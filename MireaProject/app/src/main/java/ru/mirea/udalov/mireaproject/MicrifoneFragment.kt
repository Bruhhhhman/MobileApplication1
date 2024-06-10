package ru.mirea.udalov.mireaproject

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.io.File
import android.Manifest
import androidx.core.app.ActivityCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MicrifoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val REQUEST_CODE_PERMISSION = 200

class MicrifoneFragment : Fragment() {

    private var isWork = false
    private var isStartRecording = true
    private var isStartPlaying = false
    private lateinit var recordButton: Button
    private lateinit var playButton: Button
    private lateinit var recordFilePath: String
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_micrifone, container, false)
        recordButton = view.findViewById(R.id.button)
        playButton = view.findViewById(R.id.button2)
        playButton.isEnabled = false
        recordFilePath = File(
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC),
            "/audiorecordtest.3gp"
        ).absolutePath

        requestPermissions()

        setupButtons()

        return view
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
            requireActivity(),
            permissions,
            REQUEST_CODE_PERMISSION
        )
    }

    private fun setupButtons() {
        recordButton.setOnClickListener {
            if (isStartRecording) {
                recordButton.text = "Стоп записи"
                playButton.isEnabled = false
                startRecording()
            } else {
                recordButton.text = "Начать запись"
                playButton.isEnabled = true
                stopRecording()
            }
            isStartRecording = !isStartRecording
        }

        playButton.setOnClickListener {
            if (isStartPlaying) {
                playButton.text = "Остановить"
                recordButton.isEnabled = false
                startPlaying()
            } else {
                playButton.text = "Прослушать"
                recordButton.isEnabled = true
                stopPlaying()
            }
            isStartPlaying = !isStartPlaying
        }
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(recordFilePath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            try {
                prepare()
            } catch (e: Exception) {
                Log.e("Recorder", "prepare() failed")
            }
            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            setDataSource(recordFilePath)
            prepare()
            start()
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (!isWork) {
                requireActivity().finish()
            }
        }
    }
}