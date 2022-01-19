package com.bizmiz.alphabetgame.fragments.alphabetSound

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
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bizmiz.alphabetgame.R
import com.bizmiz.alphabetgame.databinding.FragmentAlphabetSoundBinding
import com.bizmiz.alphabetgame.util.*

class AlphabetSoundFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAlphabetSoundBinding
    private lateinit var scale:Animation
    private lateinit var abcSound:MediaPlayer
    private lateinit var winSoundPlay:MediaPlayer
    private  lateinit var successLetter:String
    private  lateinit var errorSoundPlay:MediaPlayer
    private  lateinit var buttonSoundPlay:MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = FragmentAlphabetSoundBinding.bind(
            inflater.inflate(
                R.layout.fragment_alphabet_sound,
                container,
                false
            )
        )
        scale = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_alphabet)
        winSoundPlay = MediaPlayer.create(requireContext(), winSound)
        errorSoundPlay = MediaPlayer.create(requireContext(), errorSound)
        buttonSoundPlay = MediaPlayer.create(requireContext(), buttonSound)
        onClick()
        randomSound()
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnExit->{
                buttonSoundPlay.start()
                val navController: NavController =
                    Navigation.findNavController(requireActivity(), R.id.mainContainer)
                navController.popBackStack()
            }
            R.id.abc1 -> {
             animationStart(binding.abc1)
                checkLetters(binding.abc1)
            }
            R.id.abc2 -> {
                animationStart(binding.abc2)
                checkLetters(binding.abc2)
            }
            R.id.abc3 -> {
                animationStart(binding.abc3)
                checkLetters(binding.abc3)
            }
            R.id.abc4 -> {
                animationStart(binding.abc4)
                checkLetters(binding.abc4)
            }
            R.id.abc5 -> {
                animationStart(binding.abc5)
                checkLetters(binding.abc5)
            }
            R.id.abc6 -> {
                animationStart(binding.abc6)
                checkLetters(binding.abc6)
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnExit.setOnClickListener(this@AlphabetSoundFragment)
            abc1.setOnClickListener(this@AlphabetSoundFragment)
            abc2.setOnClickListener(this@AlphabetSoundFragment)
            abc3.setOnClickListener(this@AlphabetSoundFragment)
            abc4.setOnClickListener(this@AlphabetSoundFragment)
            abc5.setOnClickListener(this@AlphabetSoundFragment)
            abc6.setOnClickListener(this@AlphabetSoundFragment)
        }
    }
    private fun animationStart(abc:TextView){
       abc.startAnimation(scale)
    }
    private fun randomSound(){
        val abcSoundId = (0..25).random()
        val randomSound = abcId[abcSoundId]
        abcSound = MediaPlayer.create(requireContext(), randomSound)
        abcSound.start()
        randomLetters(abcSoundId)
    }
    private fun randomLetters(id:Int){
        val letterId:ArrayList<TextView> = arrayListOf(
            binding.abc1,binding.abc2,binding.abc3,binding.abc4,binding.abc5,binding.abc6
        )
        binding.apply {
            val letters:ArrayList<String> = arrayListOf()
            letters.addAll(abcLetter)
            successLetter = letters[id]
            val randomLetter1 = letterId.random()
            randomLetter1.text = successLetter
            letterId.remove(randomLetter1)
            letters.remove(successLetter)
            val randomLetter2 = letterId.random()
            val letter2 = letters.random()
            randomLetter2.text = letter2
            letterId.remove(randomLetter2)
            letters.remove(letter2)
            val randomLetter3 = letterId.random()
            val letter3 = letters.random()
            randomLetter3.text = letter3
            letterId.remove(randomLetter3)
            letters.remove(letter3)
            val randomLetter4 = letterId.random()
            val letter4 = letters.random()
            randomLetter4.text = letter4
            letterId.remove(randomLetter4)
            letters.remove(letter4)
            val randomLetter5 = letterId.random()
            val letter5 = letters.random()
            randomLetter5.text = letter5
            letterId.remove(randomLetter5)
            letters.remove(letter5)
            val randomLetter6 = letterId.last()
            val letter6 = letters.random()
            randomLetter6.text = letter6
        }

    }
    private fun checkLetters(txt:TextView){
        if (successLetter==txt.text){
            enabled(false)
            binding.btnExit.isEnabled = false
            winSoundPlay.start()
            binding.splash.visibility = View.VISIBLE
            binding.splash.playAnimation()
            Handler().postDelayed({
                binding.btnExit.isEnabled = true
                binding.splash.visibility = View.GONE
                enabled(true)
                randomSound()
            }, 3000)
        }else{
            enabled(false)
            errorSoundPlay.start()
            Handler().postDelayed({
                abcSound.start()
                enabled(true)
            }, 500)


        }
    }
    private fun enabled(isEnabled:Boolean){
        val letterList:ArrayList<TextView> = arrayListOf(
            binding.abc1,binding.abc2,binding.abc3,binding.abc4,binding.abc5,binding.abc6
        )
        for (i in 0..5){
            letterList[i].isEnabled = isEnabled
        }
    }
}