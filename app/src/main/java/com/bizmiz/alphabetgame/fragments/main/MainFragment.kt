package com.bizmiz.alphabetgame.fragments.main

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bizmiz.alphabetgame.R
import com.bizmiz.alphabetgame.databinding.FragmentMainBinding
import com.bizmiz.alphabetgame.util.buttonSound


class MainFragment : Fragment() {
    private lateinit var binging: FragmentMainBinding
    private var playAnim: Animation? = null
    private lateinit var left: Animation
    private lateinit var playAlpha: Animation
    private lateinit var playAlphaHide: Animation
    private lateinit var right: Animation
    private lateinit var rotate: Animation
    private lateinit var rotate_left: Animation
    private lateinit var soundBtn: MediaPlayer
    private lateinit var trainSound: MediaPlayer
    private lateinit var fonMusic: MediaPlayer
    private var check = true
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fonMusic = MediaPlayer.create(requireContext(), R.raw.music_fon)
        prefs = requireActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        left = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_left_train)
        right = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right_train)
        rotate_left = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_left)
        rotate = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        playAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale)
        playAlpha = AnimationUtils.loadAnimation(requireContext(), R.anim.alpha_play)
        playAlphaHide = AnimationUtils.loadAnimation(requireContext(), R.anim.alpha_play_hide)
        soundBtn = MediaPlayer.create(requireContext(), buttonSound)
        trainSound = MediaPlayer.create(requireContext(), R.raw.train_sound)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binging =
            FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))
        binging.btnPlay.setOnClickListener {
            playAnim = null
            binging.btnPlay.isEnabled = false
            soundBtn.start()
            binging.linerTrain.startAnimation(right)
        }
        right.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                rotateAnim()
                binging.btnPlay.startAnimation(playAlphaHide)
                binging.btnPlay.visibility = View.INVISIBLE
                trainSound.start()
            }

            override fun onAnimationEnd(animation: Animation?) {
                if (getPrefs()) {
                    val editor = prefs.edit()
                    editor.putInt("position", fonMusic.currentPosition)
                    editor.apply()
                }
                check = false
                val navController: NavController =
                    Navigation.findNavController(requireActivity(), R.id.mainContainer)
                navController.navigate(R.id.main_to_category)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        trainLeft()
        playMusicSeekTo()
        binging.btnSound.setOnClickListener {
            soundBtn.start()
            if (getPrefs()) {
                binging.btnSound.setImageResource(R.drawable.music_mute)
                fonMusic.pause()
                fonMusic.seekTo(0)
                setPrefs(false)
                check = true
            } else {
                binging.btnSound.setImageResource(R.drawable.music_sound)
                fonMusic.start()
                setPrefs(true)
                check = false
            }
        }
        if (getPrefs()) {
            check = true
            binging.btnSound.setImageResource(R.drawable.music_sound)
        } else {
            check = false
            binging.btnSound.setImageResource(R.drawable.music_mute)
        }
        playAnim?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                if (playAnim != null) {
                    binging.btnPlay.startAnimation(playAnim)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        fonMusic.setOnCompletionListener {
            if (check) {
                fonMusic.start()
            }
        }
        return binging.root

    }

    private fun playMusicSeekTo() {
        if (getPrefs()) {
            val position = prefs.getInt("position", 0)
            fonMusic.seekTo(position)
            fonMusic.start()
        }
    }

    private fun trainLeft() {
        binging.linerTrain.startAnimation(left)
        rotateAnim()
        Handler().postDelayed({
            trainSound.start()
        }, 800)
        left.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binging.btnPlay.isEnabled = false
                if (getPrefs()) {
                    fonMusic.start()
                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                binging.btnPlay.startAnimation(playAlpha)
                binging.btnPlay.visibility = View.VISIBLE
                binging.btnPlay.startAnimation(playAnim)
                trainSound.start()
                binging.btnPlay.isEnabled = true
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
    }

    private fun rotateAnim() {
        binging.trainWheel1.startAnimation(rotate)
        binging.trainWheel2.startAnimation(rotate)
        binging.trainWheel3.startAnimation(rotate)
        binging.trainWheel4.startAnimation(rotate)
        binging.trainWheelRight1.startAnimation(rotate_left)
        binging.trainWheelRight2.startAnimation(rotate_left)
        binging.trainWheelRight3.startAnimation(rotate_left)
        binging.trainWheelRight4.startAnimation(rotate_left)
        binging.trainWheelRight5.startAnimation(rotate_left)
        binging.trainWheelRight6.startAnimation(rotate_left)
    }

    override fun onPause() {
        super.onPause()
        if (getPrefs()) {
            fonMusic.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (getPrefs()) {
            fonMusic.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs.edit().remove("position").apply()
    }

    private fun setPrefs(isPlay: Boolean) {
        prefs = requireActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        prefs.edit().putBoolean("isPlay", isPlay).apply()
    }

    fun getPrefs(): Boolean {
        prefs = requireActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        return prefs.getBoolean("isPlay", true)
    }
}