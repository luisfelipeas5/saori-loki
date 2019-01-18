# SaoriLoki

When you opens this app, it will ask for a permission to be a Admin app because just with it, SaoriLoki can lock your screen without the button on the side of your device.

## First create a Receiver

Create a class that extends ```DeviceAdminReceiver``` to receive the events when the user gives the permission to the app to be a admin.

In this case, we just need the permission itself and not to be warned when this happens. So the receiver needs to be created, but it can be "empty":

```kotlin
import android.app.admin.DeviceAdminReceiver

class MyDeviceAdminReceiver: DeviceAdminReceiver()
```

## Second declare the receiver on AndroidManifest

```xml
<receiver android:name="br.com.luisfelipeas5.saoriblock.MyDeviceAdminReceiver"
          android:label="@string/sample_device_admin"
          android:description="@string/sample_device_admin_description"
          android:permission="android.permission.BIND_DEVICE_ADMIN">
    <meta-data android:name="android.app.device_admin"
               android:resource="@xml/device_admin_sample" />
    <intent-filter>
        <action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
    </intent-filter>
</receiver>
```
