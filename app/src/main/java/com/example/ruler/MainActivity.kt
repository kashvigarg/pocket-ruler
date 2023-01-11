package com.example.ruler

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.CameraView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ruler.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toast: Toast
    private lateinit var button : FloatingActionButton
    private lateinit var cameraExecutor: ExecutorService

    companion object{
        const val TAG = "MainActivity"
    }

    fun startCamera(){
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
//
//        cameraProviderFuture.addListener({
//            val cameraProvider = cameraProviderFuture.get()
//
//            val preview = Preview.Builder().build().also { it.setSurfaceProvider(binding.cameraView) }
//
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
//            } catch (exc : Exception){
//                Log.e(TAG, "UseCase Binding Failed", exc)
//            }
//        }, ContextCompat.getMainExecutor(this))
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            var cam = findViewById<CameraView>(R.id.cameraView)
            cam.bindToLifecycle(this)
        }
    }

    fun check(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
            startCamera()

        }
        else {
            Toast.makeText(this, "Please grant camera permission", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        button = findViewById<FloatingActionButton>(R.id.fab)
        button.setOnClickListener {
            toast = Toast.makeText(this, "Anchor Placed!", Toast.LENGTH_SHORT)
            Log.i(TAG, "Clicked")
            toast.show()

        }

        check()

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}

