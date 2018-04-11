package at.ikarus.kotlin.rootchecking.app

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.ArrayList

class MainActivity : AppCompatActivity()
{
    /*companion object
    {
        private fun checkRootMethod1(): Boolean
        {
            val buildTags : String = android.os.Build.TAGS
            return buildTags != null && buildTags.contains("test-keys")
        }

        private fun checkRootMethod2(): Boolean
        {
            for(path in arrayOf("/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                    "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"))
            {
                if(File(path).exists())
                    return true
            }
            return false
        }

        private fun checkRootMethod3(): Boolean
        {
            var process : Process? = null
            try
            {
                process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
                val log : BufferedReader = BufferedReader(InputStreamReader(process.inputStream))
                if(log.read() != null)
                    return true
            }
            catch(th : Throwable)
            {
                return false
            }
            finally
            {
                if(process != null)
                    process.destroy()
            }
            return  false
        }

        private fun checkRootMethod4(): Boolean
        {
            return File("/system/app/Superuser.apk").exists()
        }

        fun isRootedDevice() : Boolean
        {
            return (checkRootMethod1() || checkRootMethod2() || checkRootMethod3() || checkRootMethod4())
        }
    }*/
    private fun checkRootMethod1(): Boolean
    {
        val buildTags : String = android.os.Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod2(): Boolean
    {
        for(path in arrayOf("/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"))
        {
            if(File(path).exists())
                return true
        }
        return false
    }

    private fun checkRootMethod3(): Boolean
    {
        var process : Process? = null
        try
        {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val log : BufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            if(log.read() != null)
                return true
        }
        catch(th : Throwable)
        {
            return false
        }
        finally
        {
            if(process != null)
                process.destroy()
        }
        return  false
    }

    private fun checkRootMethod4(): Boolean
    {
        return File("/system/app/Superuser.apk").exists()
    }

    fun isRootedDevice() : Boolean
    {
        return (checkRootMethod1() || checkRootMethod2() || checkRootMethod3() || checkRootMethod4())
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            //when(MainActivity.isRootedDevice()) {
            when(isRootedDevice()) {
                true -> Snackbar.make(view, "This is a rooted devices!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                false -> Snackbar.make(view, "This is NOT a rooted devices!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId)
        {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
