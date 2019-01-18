package br.com.luisfelipeas5.saoriblock

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lock.*

class LockActivity : AppCompatActivity() {

    companion object {
        private const val ADD_DEVICE_ADMIN_REQUEST_CODE = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)

        btLock.setOnClickListener { onLockButtonClicked() }
        onLockButtonClicked()
    }

    private fun onLockButtonClicked(askForPermissionIfNecessary: Boolean = true) {
        val policy = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        try {
            policy.lockNow()
        } catch (ex: SecurityException) {
            if (askForPermissionIfNecessary) {
                askPermissionToBeAdmin()
            }
        }
    }

    private fun askPermissionToBeAdmin() {
        Toast.makeText(this, getString(R.string.needs_admin), Toast.LENGTH_LONG).show()

        val admin = ComponentName(this, MyDeviceAdminReceiver::class.java)
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, admin)
        startActivityForResult(intent, ADD_DEVICE_ADMIN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ADD_DEVICE_ADMIN_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    onLockButtonClicked(false)
                }
            }
        }
    }

}
