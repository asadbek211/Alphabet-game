package com.bizmiz.alphabetgame.fragments.memoryMatch

import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bizmiz.alphabetgame.R
import com.bizmiz.alphabetgame.databinding.FragmentMemoryMatchBinding
import com.bizmiz.alphabetgame.util.*

class MemoryMatchFragment : Fragment() {
    private var clicked = 0
    private var turnOver = false
    private var lastClicked = -1
    private val cardBack = R.drawable.ic_group_23
    private lateinit var buttons: Array<AppCompatButton>
    private lateinit var binding: FragmentMemoryMatchBinding
    private lateinit var images: MutableList<Int>
    private lateinit var list: MutableList<MutableList<Int>>
    private lateinit var buttonSoundPlay: MediaPlayer
    private lateinit var winSoundPlay: MediaPlayer
    private lateinit var successSoundPlay: MediaPlayer
    private lateinit var errorSoundPlay: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = FragmentMemoryMatchBinding.bind(
            inflater.inflate(
                R.layout.fragment_memory_match,
                container,
                false
            )
        )
        buttonSoundPlay = MediaPlayer.create(requireContext(), buttonSound)
        errorSoundPlay = MediaPlayer.create(requireContext(), errorSound)
        winSoundPlay = MediaPlayer.create(requireContext(), winSound)
        successSoundPlay = MediaPlayer.create(requireContext(), successSound)
        buttons = arrayOf(
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9,
            binding.btn10,
            binding.btn11,
            binding.btn12
        )

        binding.btnExit.setOnClickListener {
            buttonSoundPlay.start()
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.mainContainer)
            navController.popBackStack()
        }
        list = mutableListOf(
            images1,
            images2,
            images3,
            images4,
            images5,
            images6,
            images7,
            images8,
            images9,
            images10,
            images11
        )
        randomImgList()
        startGame()
        return binding.root
    }

    private fun randomImgList() {
        binding.splash.visibility = View.GONE
        images = list.random()
        images.shuffle()
    }

    private fun startGame() {
        for (i in 0..11) {
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                }
                if (clicked == 2) {
                    for (pos in 0..11) {
                        buttons[pos].isClickable = false
                    }
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        successSoundPlay.start()
                        Handler().postDelayed({
                            buttons[i].visibility = View.INVISIBLE
                            buttons[lastClicked].visibility = View.INVISIBLE
                            buttons[i].isEnabled = false
                            buttons[lastClicked].isEnabled = false
                            turnOver = false
                            clicked = 0
                            binding.apply {
                                if (!btn1.isEnabled && !btn2.isEnabled && !btn3.isEnabled && !btn4.isEnabled &&
                                    !btn5.isEnabled && !btn6.isEnabled && !btn7.isEnabled && !btn8.isEnabled &&
                                    !btn9.isEnabled && !btn10.isEnabled && !btn11.isEnabled && !btn12.isEnabled
                                ) {
                                    winSoundPlay.start()
                                    binding.splash.visibility = View.VISIBLE
                                    binding.splash.playAnimation()
                                    Handler().postDelayed({
                                        randomImgList()
                                        startGame()
                                        for (i in 0..11) {
                                            visibility(i)
                                        }
                                        for (pos in 0..11) {
                                            buttons[pos].isClickable = true
                                        }
                                    }, 3000)

                                }
                            }
                            for (pos in 0..11) {
                                buttons[pos].isClickable = true
                            }
                        }, 600)
                    } else {
                        errorSoundPlay.start()
                        Handler().postDelayed({
                            buttons[i].setBackgroundResource(cardBack)
                            buttons[lastClicked].setBackgroundResource(cardBack)
                            buttons[i].text = "cardBack"
                            buttons[lastClicked].text = "cardBack"
                            turnOver = false
                            clicked = 0
                            for (pos in 0..11) {
                                buttons[pos].isClickable = true
                            }
                        }, 800)
                    }
                }
            }
        }
    }

    private fun visibility(i: Int) {
        buttons[i].visibility = View.VISIBLE
        buttons[i].isEnabled = true
        buttons[i].setBackgroundResource(cardBack)
    }
}