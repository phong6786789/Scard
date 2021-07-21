package com.subi.scard.view.fragment.qr_code

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentScanQRBinding
import com.subi.scard.utils.Constants
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

@Suppress("DEPRECATION")
class ScanQRFragment : BaseBindingFragment<FragmentScanQRBinding, QRViewmodel>(),
    ZBarScannerView.ResultHandler {
    var bottomNavigation:MeowBottomNavigation?=null
    lateinit var scannerView: ZBarScannerView
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: QRViewmodel
        get() = ViewModelProviders.of(this).get(QRViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_scan_q_r

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        checkForPermission()

        //Hide bottom
        bottomNavigation = activity?.findViewById(R.id.bottomNavigation)
        bottomNavigation?.visibility = View.GONE
    }

    private fun onClicks() {
        viewDataBinding?.apply {
            flashLight.setOnClickListener {
                if (it.isSelected) {
                    //Tắt đèn cam
                    it.isSelected = false
                    scannerView.flash = false
                } else {
                    //Bật đèn cam
                    it.isSelected = true
                    scannerView.flash = true
                }
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = Constants.TITLE.QR
    }

    override fun handleResult(rawResult: Result?) {
        //Trả kết quả về
        Toast.makeText(context, rawResult?.contents.toString(), Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_scanQRFragment_to_showCardFragment)
    }

    private fun initViews() {
        initializeQRCamera()
    }

    private fun initializeQRCamera() {
        scannerView = ZBarScannerView(context)
        scannerView.setResultHandler(this)
        context?.let {
            scannerView.setBackgroundColor(ContextCompat.getColor(it, R.color.bg_qr))
            scannerView.setBorderColor(ContextCompat.getColor(it, R.color.blue3_main))
            scannerView.setLaserColor(ContextCompat.getColor(it, R.color.blue3_main))
        }
        scannerView.setBorderStrokeWidth(10)
        scannerView.setSquareViewFinder(true)
        scannerView.setupScanner()
        scannerView.setAutoFocus(true)
        startQRCamera()
        viewDataBinding?.containerScanner?.addView(scannerView)
    }

    private fun startQRCamera() {
        scannerView.startCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }

    private fun checkForPermission() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } == PackageManager.PERMISSION_GRANTED) {
            initViews()
            onClicks()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it, arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 102
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initViews()
                onClicks()
            } else if (isPermanentlyDenied()) {
                showGoToAppSettingsDialog()
            } else
                requestPermission()
        }
    }

    private fun isPermanentlyDenied(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA).not()
        } else {
            return false
        }
    }


    private fun showGoToAppSettingsDialog() {
        context?.let {
            AlertDialog.Builder(it, R.style.CustomAlertDialog)
                .setTitle("CẢNH BÁO")
                .setMessage("Bạn cần cấp quyền camera để quét mã!")
                .setPositiveButton("Đồng ý") { _, _ ->
                    goToAppSettings()
                }
                .setNegativeButton("Không đồng ý") { _, _ ->
                    run {
                        activity?.finish()
                    }
                }.show()
        }
    }

    private fun goToAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity?.packageName, null)
        )
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        checkForPermission()
    }
}