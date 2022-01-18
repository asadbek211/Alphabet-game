package com.bizmiz.alphabetgame.fragments.spelling

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bizmiz.alphabetgame.R
import com.bizmiz.alphabetgame.databinding.FragmentSpellingBinding
import com.bizmiz.alphabetgame.util.*


class SpellingFragment : Fragment() {
    private var dX1 = 0f
    private var dY1 = 0f
    private var dX2 = 0f
    private var dY2 = 0f
    private var dX3 = 0f
    private var dY3 = 0f
    private var startX1 = 0f
    private var startY1 = 0f
    private var startX2 = 0f
    private var startY2 = 0f
    private var startX3 = 0f
    private var startY3 = 0f
    private var randNum = 0
    private var randNum1 = 0
    private var randNum2 = 0
    private var check = true
    private lateinit var binding: FragmentSpellingBinding
    private lateinit var buttonSoundPlay: MediaPlayer
    private lateinit var winSoundPlay: MediaPlayer
    private lateinit var errorSoundPlay: MediaPlayer
    private lateinit var successSoundPlay: MediaPlayer
    private lateinit var letterPlayer: MediaPlayer
    private lateinit var scale: Animation

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        buttonSoundPlay = MediaPlayer.create(requireContext(), buttonSound)
        errorSoundPlay = MediaPlayer.create(requireContext(), errorSound)
        successSoundPlay = MediaPlayer.create(requireContext(), successSound)
        scale = AnimationUtils.loadAnimation(requireContext(), R.anim.scale)
        binding = FragmentSpellingBinding.bind(
            inflater.inflate(
                R.layout.fragment_spelling,
                container,
                false
            )
        )
        randomLetters(binding.ch1, binding.ch2, binding.ch3)
        binding.btnExit.setOnClickListener {
            buttonSoundPlay.start()
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.mainContainer)
            navController.popBackStack()
        }
        binding.imgSpelling.setOnClickListener {
            if (!letterPlayer.isPlaying){
                letterSound(letterId[randNum])
            }
        }
        inputCh1onTouch()
        inputCh2onTouch()
        inputCh3onTouch()
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")

    private fun inputCh1onTouch() {
        val inputCh1Player =
            MediaPlayer.create(requireContext(), checkABC(binding.inputCh1.text.toString()))
        binding.apply {
            inputCh1.setOnTouchListener { v, event ->
                if (!inputCh1Player.isPlaying && !letterPlayer.isPlaying && check) {
                    inputCh1Player.start()
                    check = false
                }
                startX1 = inputCh1.left.toFloat()
                startY1 = inputCh1.top.toFloat()
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        when (inputCh1.text) {
                            ch1.text -> {
                                inputChecked(inputCh1, ch1, startX1, startY1)
                            }
                            ch2.text -> {
                                inputChecked(inputCh1, ch2, startX1, startY1)
                            }
                            ch3.text -> {
                                inputChecked(inputCh1, ch3, startX1, startY1)
                            }
                        }
                        check = true
                    }
                    MotionEvent.ACTION_DOWN -> {
                        dX1 = v.x - event.rawX
                        dY1 = v.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        v.animate()
                            .x(event.rawX + dX1)
                            .y(event.rawY + dY1)
                            .setDuration(0)
                            .start()
                    }
                    else -> return@setOnTouchListener false
                }
                return@setOnTouchListener true
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun inputCh2onTouch() {
        val inputCh2Player =
            MediaPlayer.create(requireContext(), checkABC(binding.inputCh2.text.toString()))
        binding.apply {
            inputCh2.setOnTouchListener { v, event ->
                if (!inputCh2Player.isPlaying && !letterPlayer.isPlaying && check) {
                    inputCh2Player.start()
                    check = false
                }
                startX2 = inputCh2.left.toFloat()
                startY2 = inputCh2.top.toFloat()
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        when (inputCh2.text) {
                            ch1.text -> {
                                inputChecked(inputCh2, ch1, startX2, startY2)
                            }
                            ch2.text -> {
                                inputChecked(inputCh2, ch2, startX2, startY2)
                            }
                            ch3.text -> {
                                inputChecked(inputCh2, ch3, startX2, startY2)
                            }
                        }
                        check = true
                    }
                    MotionEvent.ACTION_DOWN -> {
                        dX2 = v.x - event.rawX
                        dY2 = v.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        v.animate()
                            .x(event.rawX + dX2)
                            .y(event.rawY + dY2)
                            .setDuration(0)
                            .start()
                    }
                    else -> return@setOnTouchListener false
                }
                return@setOnTouchListener true
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun inputCh3onTouch() {
        val inputCh3Player =
            MediaPlayer.create(requireContext(), checkABC(binding.inputCh3.text.toString()))
        binding.apply {
            inputCh3.setOnTouchListener { v, event ->
                if (!inputCh3Player.isPlaying && !letterPlayer.isPlaying && check) {
                    inputCh3Player.start()
                    check = false
                }
                startX3 = inputCh3.left.toFloat()
                startY3 = inputCh3.top.toFloat()
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        when (inputCh3.text) {
                            ch1.text -> {
                                inputChecked(inputCh3, ch1, startX3, startY3)
                            }
                            ch2.text -> {
                                inputChecked(inputCh3, ch2, startX3, startY3)
                            }
                            ch3.text -> {
                                inputChecked(inputCh3, ch3, startX3, startY3)
                            }
                        }
                        check = true

                    }
                    MotionEvent.ACTION_DOWN -> {
                        dX3 = v.x - event.rawX
                        dY3 = v.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        v.animate()
                            .x(event.rawX + dX3)
                            .y(event.rawY + dY3)
                            .setDuration(0)
                            .start()
                    }
                    else -> return@setOnTouchListener false
                }
                return@setOnTouchListener true
            }
        }

    }

    private fun checked(inputCh1: TextView, ch1: TextView): Boolean {
        return ((inputCh1.x + inputCh1.width / 4 > ch1.x && ch1.x + ch1.width / 4 > inputCh1.x + inputCh1.width / 4) && (inputCh1.y > ch1.y && inputCh1.y < ch1.y + ch1.height / 4)) ||
                ((inputCh1.x > ch1.x && inputCh1.x < ch1.x + ch1.width / 4) && (inputCh1.y > ch1.y && inputCh1.y < ch1.y + ch1.height / 4)) ||
                ((inputCh1.x + inputCh1.width / 4 > ch1.x && ch1.x + ch1.width / 4 > inputCh1.x + inputCh1.width / 4) && (inputCh1.y + inputCh1.height / 4 > ch1.y && inputCh1.y + inputCh1.height / 4 < ch1.y + ch1.height / 4)) ||
                ((inputCh1.x > ch1.x && inputCh1.x < ch1.x + ch1.width / 4) && (inputCh1.y + inputCh1.height / 4 > ch1.y && inputCh1.y + inputCh1.height / 4 < ch1.y + ch1.height / 4))
    }

    private fun randomLetters(ch1: TextView, ch2: TextView, ch3: TextView) {
        val letterList: ArrayList<Char> = arrayListOf()
            randNum = (0..64).random()
            val letterSound = letterId[randNum]
            letterSound(letterSound)
        val letter = lettersList[randNum]
        binding.imgSpelling.setImageResource(letterImages[randNum])
        letterList.add(letter[0])
        letterList.add(letter[1])
        letterList.add(letter[2])
        ch1.text = letterList[0].toString()
        ch2.text = letterList[1].toString()
        ch3.text = letterList[2].toString()
            randNum1 = (0..2).random()
        val character1 = letterList[randNum1]
        binding.inputCh1.text = character1.toString()
        letterList.remove(character1)
            randNum2 = (0..1).random()
        val character2 = letterList[randNum2]
        binding.inputCh2.text = character2.toString()
        letterList.remove(character2)
        binding.inputCh3.text = letterList[0].toString()
    }

    private fun inputChecked(inputCh: TextView, ch: TextView, startX: Float, startY: Float) {
        if (checked(inputCh, ch)) {
            successSoundPlay.start()
            inputCh.x = ch.left.toFloat() + 8f
            inputCh.y = ch.top.toFloat() + 11f
            inputCh.isEnabled = false

            binding.apply {
                if (liner.x < inputCh1.x && liner.x < inputCh2.x && liner.x < inputCh3.x &&
                    liner.x + liner.width > inputCh1.x && liner.x + liner.width > inputCh2.x && liner.x + liner.width > inputCh3.x &&
                    liner.y < inputCh1.y && liner.y < inputCh2.y && liner.y < inputCh3.y &&
                    liner.y + liner.height > inputCh1.y && liner.y + liner.height > inputCh2.y && liner.y + liner.height > inputCh3.y
                ) {
                    splash.visibility = View.VISIBLE
                    splash.playAnimation()
                    effect.visibility = View.VISIBLE
                    effect.repeatCount = 1
                    effect.playAnimation()
                    burger.visibility = View.VISIBLE
                    burger.playAnimation()
                    winSoundPlay = MediaPlayer.create(requireContext(), winSound)
                    winSoundPlay.start()
                    letterSound(letterId[randNum])
                    btnExit.isEnabled = false
                    Handler().postDelayed({
                      next()
                    }, 3000)
                }
            }
        } else {
            binding.apply {
                if (liner.x<=inputCh.x && liner.y <=inputCh.y && liner.x+liner.width>=inputCh.x+inputCh.width &&
                    liner.y+liner.height>=inputCh.y+inputCh.height){
                    errorSoundPlay.start()
                }
            }
            inputCh.x = startX
            inputCh.y = startY

        }
       }

    private fun letterSound(id: Int) {
        letterPlayer = MediaPlayer.create(requireContext(), id)
        if (!letterPlayer.isPlaying) {
            binding.imgSpelling.startAnimation(scale)
            letterPlayer.start()
        }
    }
    private fun next(){
        binding.apply {
            inputCh1.x = startX1
            inputCh1.y = startY1
            inputCh2.x = startX2
            inputCh2.y = startY2
            inputCh3.x = startX3
            inputCh3.y = startY3
            inputCh1.isEnabled = true
            inputCh2.isEnabled = true
            inputCh3.isEnabled = true
            btnExit.isEnabled = true
            splash.visibility = View.INVISIBLE
            burger.visibility = View.INVISIBLE
            effect.visibility = View.INVISIBLE
            randomLetters(ch1, ch2, ch3)
            inputCh1onTouch()
            inputCh2onTouch()
            inputCh3onTouch()
        }
    }
}