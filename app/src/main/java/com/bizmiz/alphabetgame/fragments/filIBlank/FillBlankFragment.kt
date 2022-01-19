package com.bizmiz.alphabetgame.fragments.filIBlank

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
import com.bizmiz.alphabetgame.databinding.FragmentFillBlankBinding
import com.bizmiz.alphabetgame.util.*

class FillBlankFragment : Fragment() {
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
    private lateinit var randTextView: TextView
    private var check = true
    private lateinit var binding: FragmentFillBlankBinding
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
        binding = FragmentFillBlankBinding.bind(
            inflater.inflate(
                R.layout.fragment_fill_blank,
                container,
                false
            )
        )
        randomLetters(binding.inputCh1Fon, binding.inputCh2Fon, binding.inputCh3Fon)
        binding.btnExit.setOnClickListener {
            buttonSoundPlay.start()
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.mainContainer)
            navController.popBackStack()
        }
        binding.imgSpelling.setOnClickListener {
            letterSound(letterId[randNum])
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
                        if (!checkImg(binding.inputCh2)) {
                            binding.inputCh2.isEnabled = true
                            if (startX2 != 0f && startY2 != 0f) {
                                binding.inputCh2.x = startX2
                                binding.inputCh2.y = startY2
                            }
                        }
                        if (!checkImg(binding.inputCh3)) {
                            binding.inputCh3.isEnabled = true
                            if (startX3 != 0f && startY3 != 0f) {
                                binding.inputCh3.x = startX3
                                binding.inputCh3.y = startY3
                            }
                        }
                        inputChecked(inputCh1, startX1, startY1)
                        check = true
                    }
                    MotionEvent.ACTION_DOWN -> {
                        dX1 = v.x - event.rawX
                        dY1 = v.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        binding.inputCh2.isEnabled = false
                        binding.inputCh3.isEnabled = false
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
                        if (!checkImg(binding.inputCh1)) {
                            binding.inputCh1.isEnabled = true
                            if (startX1 != 0f && startY1 != 0f) {
                                binding.inputCh1.x = startX1
                                binding.inputCh1.y = startY1
                            }
                        }
                        if (!checkImg(binding.inputCh3)) {
                            binding.inputCh3.isEnabled = true
                            if (startX3 != 0f && startY3 != 0f) {
                                binding.inputCh3.x = startX3
                                binding.inputCh3.y = startY3
                            }
                        }
                        inputChecked(inputCh2, startX2, startY2)
                        check = true
                    }
                    MotionEvent.ACTION_DOWN -> {
                        dX2 = v.x - event.rawX
                        dY2 = v.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        binding.inputCh1.isEnabled = false
                        binding.inputCh3.isEnabled = false
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
                        if (!checkImg(binding.inputCh1)) {
                            binding.inputCh1.isEnabled = true
                            if (startX1 != 0f && startY1 != 0f) {
                                binding.inputCh1.x = startX1
                                binding.inputCh1.y = startY1
                            }
                        }
                        if (!checkImg(binding.inputCh2)) {
                            binding.inputCh2.isEnabled = true
                            if (startX2 != 0f && startY2 != 0f) {
                                binding.inputCh2.x = startX2
                                binding.inputCh2.y = startY2
                            }
                        }
                        inputChecked(inputCh3, startX3, startY3)
                        check = true

                    }
                    MotionEvent.ACTION_DOWN -> {
                        dX3 = v.x - event.rawX
                        dY3 = v.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        binding.inputCh1.isEnabled = false
                        binding.inputCh2.isEnabled = false
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
        val inputChFonId: ArrayList<TextView> = arrayListOf(
            binding.inputCh1Fon,
            binding.inputCh2Fon,
            binding.inputCh3Fon
        )
        val backgroundId: ArrayList<Int> = arrayListOf(
            R.drawable.shape_h1,
            R.drawable.shape_h2,
            R.drawable.shape_h3,
        )
        val random1 = backgroundId.random()
        val randomCh1 = inputChFonId.random()
        randomCh1.setBackgroundResource(random1)
        backgroundId.remove(random1)
        inputChFonId.remove(randomCh1)
        val random2 = backgroundId.random()
        val randomCh2 = inputChFonId.random()
        randomCh2.setBackgroundResource(random2)
        backgroundId.remove(random2)
        inputChFonId.remove(randomCh2)
        randTextView = inputChFonId.last()
//            Toast.makeText(requireActivity(), "${inputChFonId.last()}\n${backgroundId.last()}", Toast.LENGTH_SHORT).show()

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
        when (randTextView.text) {
            binding.inputCh1.text -> {
                binding.inputCh1.setBackgroundResource(backgroundId.last())
                binding.inputCh2.setBackgroundResource(random1)
                binding.inputCh3.setBackgroundResource(random2)
            }
            binding.inputCh2.text -> {
                binding.inputCh2.setBackgroundResource(backgroundId.last())
                binding.inputCh1.setBackgroundResource(random1)
                binding.inputCh3.setBackgroundResource(random2)
            }
            binding.inputCh3.text -> {
                binding.inputCh3.setBackgroundResource(backgroundId.last())
                binding.inputCh2.setBackgroundResource(random1)
                binding.inputCh1.setBackgroundResource(random2)
            }
        }
    }

    private fun inputChecked(inputCh: TextView, startX: Float, startY: Float) {
        if (checked(inputCh, randTextView) && inputCh.text == randTextView.text) {
            successSoundPlay.start()
            inputCh.x = randTextView.left.toFloat()
            inputCh.y = randTextView.top.toFloat()
            binding.apply {
                btnExit.isEnabled = false
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
                inputCh1.isEnabled = false
                inputCh2.isEnabled = false
                inputCh3.isEnabled = false
                Handler().postDelayed({
                    next()
                }, 3000)
            }
        } else {
            binding.apply {
                if (liner.x <= inputCh.x && liner.y <= inputCh.y && liner.x + liner.width >= inputCh.x + inputCh.width &&
                    liner.y + liner.height >= inputCh.y + inputCh.height
                ) {
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

    private fun next() {
        binding.apply {
            splash.visibility = View.GONE
            burger.visibility = View.GONE
            effect.visibility = View.GONE
            inputCh1.isEnabled = true
            inputCh2.isEnabled = true
            inputCh3.isEnabled = true
            when (randTextView.text) {
                inputCh1.text -> {
                    inputCh1.x = startX1
                    inputCh1.y = startY1
                }
                inputCh2.text -> {
                    inputCh2.x = startX2
                    inputCh2.y = startY2
                }
                inputCh3.text -> {
                    inputCh3.x = startX3
                    inputCh3.y = startY3
                }
            }
            inputCh1Fon.setBackgroundResource(R.color.white)
            inputCh2Fon.setBackgroundResource(R.color.white)
            inputCh3Fon.setBackgroundResource(R.color.white)
            randomLetters(inputCh1Fon, inputCh2Fon, inputCh3Fon)
            btnExit.isEnabled = true
            inputCh1onTouch()
            inputCh2onTouch()
            inputCh3onTouch()
        }
    }
    private fun checkImg(inputCh: TextView): Boolean {
        return binding.liner.x < inputCh.x && binding.liner.x + binding.liner.width > inputCh.x + inputCh.width &&
                binding.liner.y < inputCh.y && binding.liner.y + binding.liner.height > inputCh.y + inputCh.height
    }
}