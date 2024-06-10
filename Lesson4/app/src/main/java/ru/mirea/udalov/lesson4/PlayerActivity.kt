package ru.mirea.udalov.lesson4

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.udalov.lesson4.databinding.ActivityPlayerBinding


class PlayerActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var binding: ActivityPlayerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        loadMedia()

        binding!!.buttonPlayPause.setOnClickListener {
            if (isPlaying) {
                pauseMedia()
            } else {
                playMedia()
            }
        }

        binding!!.seekBarProgress.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                if (isPlaying) {
                    pauseMedia()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (mediaPlayer != null && !isPlaying) {
                    playMedia()
                }
            }
        })

        binding!!.buttonPrevious.setOnClickListener {
            pauseMedia()
            mediaPlayer?.seekTo(0)
            playMedia()
        }


        binding!!.buttonNext.setOnClickListener { nextMedia() }
    }

    private fun loadMedia() {
        mediaPlayer = MediaPlayer.create(this, R.raw.track)
        mediaPlayer?.let {
            binding!!.seekBarProgress.max = it.duration
        }
    }


    private fun playMedia() {
        mediaPlayer?.start()
        isPlaying = true
        updatePlayPauseButton()
        updateSeekBar()
    }


    private fun pauseMedia() {
        mediaPlayer?.pause()
        isPlaying = false
        updatePlayPauseButton()
    }

    private fun nextMedia() {
        mediaPlayer?.seekTo(0)
        mediaPlayer?.pause()
        isPlaying = false
        updatePlayPauseButton()
    }


    private fun updatePlayPauseButton() {

    }

    private fun updateSeekBar() {
        if (mediaPlayer != null && isPlaying) {
            val updateSeekBar = Runnable { updateSeekBar() }
            binding!!.seekBarProgress.postDelayed(updateSeekBar, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}