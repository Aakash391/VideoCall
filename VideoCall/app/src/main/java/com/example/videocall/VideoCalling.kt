package com.example.videocall

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.facebook.react.modules.core.PermissionListener
import com.facebook.react.uimanager.ReactAccessibilityDelegate.setRole
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo
import org.jitsi.meet.sdk.JitsiMeetView

class VideoCalling : FragmentActivity(), JitsiMeetActivityInterface {
    private var view:JitsiMeetView? =null

//    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        JitsiMeetActivityDelegate.onActivityResult(this,requestCode,resultCode,data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        JitsiMeetActivityDelegate.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_video_calling)

        val videocall =findViewById<FrameLayout>(R.id.videoCall)
        val callInt = intent
        val callId =callInt.getStringExtra("callId")

        view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("https://meet.jit.si/$callId")
            
            .build()

        view!!.join(options)
        videocall.addView(view)



    }
//    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        super.onDestroy()
        Log.d("VideoCalling", "onDestroy() called")
        view!!.dispose()
        view =null
        JitsiMeetActivityDelegate.onHostDestroy(this)
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
//        JitsiMeetActivityDelegate.onBackPressed()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        JitsiMeetActivityDelegate.onNewIntent(intent)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }


    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        JitsiMeetActivityDelegate.onHostResume(this)
    }

    override fun onStop() {
        super.onStop()
        JitsiMeetActivityDelegate.onHostPause(this)

    }
}