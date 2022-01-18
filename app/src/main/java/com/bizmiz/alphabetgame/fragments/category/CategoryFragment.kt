package com.bizmiz.alphabetgame.fragments.category

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bizmiz.alphabetgame.R
import com.bizmiz.alphabetgame.databinding.FragmentCategoryBinding
import com.bizmiz.alphabetgame.util.buttonSound


class CategoryFragment : Fragment(), View.OnClickListener {
    private lateinit var binging: FragmentCategoryBinding
    private lateinit var rotate_left: Animation
    private lateinit var rotate: Animation
    private lateinit var left: Animation
    private lateinit var right: Animation
    private lateinit var trainSound: MediaPlayer
    private lateinit var buttonSoundCategory: MediaPlayer
    private lateinit var fonSound: MediaPlayer
    private lateinit var prefs: SharedPreferences
    private var position = 0
    private var check = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        prefs = requireActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        position = prefs.getInt("position", 0)
        fonSound = MediaPlayer.create(requireContext(), R.raw.music_fon)
        if (getPrefs()) {
            fonSound.seekTo(position)
            fonSound.start()
        }
        rotate_left = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_left)
        rotate = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        left = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_left_train_category)
        right = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right_train_category)
        trainSound = MediaPlayer.create(requireContext(), R.raw.train_sound)
        buttonSoundCategory = MediaPlayer.create(requireContext(), buttonSound)
        binging = FragmentCategoryBinding.bind(
            inflater.inflate(
                R.layout.fragment_category,
                container,
                false
            )
        )
        binging.btnExit.setOnClickListener(this)
        binging.img1.setOnClickListener(this)
        binging.img2.setOnClickListener(this)
        binging.img3.setOnClickListener(this)
        binging.img4.setOnClickListener(this)
        binging.img5.setOnClickListener(this)
        trainLeft()
           fonSound.setOnCompletionListener {
               if (check){
                   fonSound.start()
               }
           }

        left.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binging.btnExit.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animation?) {
                binging.btnExit.isEnabled = true
                trainSound.start()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        binging.btnSound.setOnClickListener {
            buttonSoundCategory.start()
            if (getPrefs()) {
                binging.btnSound.setImageResource(R.drawable.music_mute_right_)
                fonSound.pause()
                fonSound.seekTo(0)
                setPrefs(false)
                check = true
            } else {
                binging.btnSound.setImageResource(R.drawable.music_sound_right)
                fonSound.start()
               setPrefs(true)
                check = false
            }
        }
        if (getPrefs()) {
            check = true
            binging.btnSound.setImageResource(R.drawable.music_sound_right)
        } else {
            check = false
            binging.btnSound.setImageResource(R.drawable.music_mute_right_)
        }
        return binging.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnExit -> {
                binging.btnExit.isEnabled = false
                buttonSoundCategory.start()
                trainRight()
                right.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        destination(0)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                })
            }
            R.id.img1 -> {
                destination(R.id.category_to_spelling)
                buttonSoundCategory.start()
            }
            R.id.img2 -> {
                destination(R.id.category_to_fillBlank)
                buttonSoundCategory.start()
            }
            R.id.img3 -> {
                destination(R.id.category_to_blankSpelling)
                buttonSoundCategory.start()
            }
            R.id.img4 -> {
                destination(R.id.category_to_alphabetSound)
                buttonSoundCategory.start()
            }
            R.id.img5 -> {
                destination(R.id.category_to_memoryMatch)
                buttonSoundCategory.start()
            }
        }
    }

    private fun destination(id: Int) {
        val navController: NavController =
            Navigation.findNavController(requireActivity(), R.id.mainContainer)
        if (id == 0) {
            navController.popBackStack()
        } else {
            navController.navigate(id)
        }
        if (getPrefs()){
            val editor = prefs.edit()
            editor.putInt("position", fonSound.currentPosition)
            editor.apply()
        }
        check = false
    }

    private fun trainLeft() {
        trainSound.start()
        binging.linerTrain.startAnimation(left)
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

    private fun trainRight() {
        trainSound.start()
        binging.linerTrain.startAnimation(right)
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
            fonSound.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (getPrefs()) {
            fonSound.start()
        }
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