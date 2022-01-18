package com.bizmiz.alphabetgame.util

import com.bizmiz.alphabetgame.R

const val buttonSound = R.raw.back
const val winSound = R.raw.win_sound
const val errorSound = R.raw.nono
const val successSound = R.raw.done

val letterId: ArrayList<Int> = arrayListOf(
    R.raw.letter0,
    R.raw.letter1,
    R.raw.letter2,
    R.raw.letter3,
    R.raw.letter4,
    R.raw.letter5,
    R.raw.letter6,
    R.raw.letter7,
    R.raw.letter8,
    R.raw.letter9,
    R.raw.letter10,
    R.raw.letter11,
    R.raw.letter12,
    R.raw.letter13,
    R.raw.letter14,
    R.raw.letter15,
    R.raw.letter16,
    R.raw.letter17,
    R.raw.letter18,
    R.raw.letter19,
    R.raw.letter20,
    R.raw.letter21,
    R.raw.letter22,
    R.raw.letter23,
    R.raw.letter24,
    R.raw.letter25,
    R.raw.letter26,
    R.raw.letter27,
    R.raw.letter28,
    R.raw.letter29,
    R.raw.letter30,
    R.raw.letter31,
    R.raw.letter32,
    R.raw.letter33,
    R.raw.letter34,
    R.raw.letter35,
    R.raw.letter36,
    R.raw.letter37,
    R.raw.letter38,
    R.raw.letter39,
    R.raw.letter40,
    R.raw.letter41,
    R.raw.letter42,
    R.raw.letter43,
    R.raw.letter44,
    R.raw.letter45,
    R.raw.letter46,
    R.raw.letter47,
    R.raw.letter48,
    R.raw.letter49,
    R.raw.letter50,
    R.raw.letter51,
    R.raw.letter52,
    R.raw.letter53,
    R.raw.letter54,
    R.raw.letter55,
    R.raw.letter56,
    R.raw.letter57,
    R.raw.letter58,
    R.raw.letter59,
    R.raw.letter60,
    R.raw.letter61,
    R.raw.letter62,
    R.raw.letter63,
    R.raw.letter64
)

val abcId: ArrayList<Int> = arrayListOf(
    R.raw.abc0,
    R.raw.abc1,
    R.raw.abc2,
    R.raw.abc3,
    R.raw.abc4,
    R.raw.abc5,
    R.raw.abc6,
    R.raw.abc7,
    R.raw.abc8,
    R.raw.abc9,
    R.raw.abc10,
    R.raw.abc11,
    R.raw.abc12,
    R.raw.abc13,
    R.raw.abc14,
    R.raw.abc15,
    R.raw.abc16,
    R.raw.abc17,
    R.raw.abc18,
    R.raw.abc19,
    R.raw.abc20,
    R.raw.abc21,
    R.raw.abc22,
    R.raw.abc23,
    R.raw.abc24,
    R.raw.abc25
)

val lettersList: ArrayList<String> = arrayListOf(
    "RAT",
    "MAT",
    "FAN",
    "BAG",
    "CAT",
    "BED",
    "JET",
    "NET",
    "HEN",
    "PEN",
    "SIX",
    "INK",
    "LID",
    "TIN",
    "ZIP",
    "BOX",
    "COB",
    "BOY",
    "LOG",
    "DOG",
    "COT",
    "CAP",
    "BUN",
    "CUP",
    "HUT",
    "JUG",
    "SUN",
    "BUS",
    "HAT",
    "CAN",
    "VAN",
    "MAP",
    "RED",
    "TED",
    "TEN",
    "PET",
    "WET",
    "HIT",
    "SIT",
    "PIG",
    "WIN",
    "TOP",
    "DOT",
    "HOT",
    "POT",
    "CUT",
    "NUT",
    "BUG",
    "MUG",
    "RUN",
    "JAM",
    "GEM",
    "LEG",//new
    "COW",
    "EAR",
    "ONE",
    "TOY",
    "ANT",
    "TEA",
    "FOX",
    "FOG",
    "PEA",
    "PIE",
    "SEA",
    "SAD" //65
)
val letterImages: ArrayList<Int> = arrayListOf(
    R.drawable.img2,
    R.drawable.img3,
    R.drawable.img4,
    R.drawable.img5,
    R.drawable.img6,
    R.drawable.img7,
    R.drawable.img8,
    R.drawable.img9,
    R.drawable.img10,
    R.drawable.img11,
    R.drawable.img12,
    R.drawable.img13,
    R.drawable.img14,
    R.drawable.img15,
    R.drawable.img16,
    R.drawable.img17,
    R.drawable.img18,
    R.drawable.img19,
    R.drawable.img20,
    R.drawable.img21,
    R.drawable.img22,
    R.drawable.img23,
    R.drawable.img24,
    R.drawable.img25,
    R.drawable.img26,
    R.drawable.img27,
    R.drawable.img28,
    R.drawable.img29,
    R.drawable.img30,
    R.drawable.img31,
    R.drawable.img32,
    R.drawable.img33,
    R.drawable.img34,
    R.drawable.img35,
    R.drawable.img36,
    R.drawable.img37,
    R.drawable.img38,
    R.drawable.img39,
    R.drawable.img40,
    R.drawable.img41,
    R.drawable.img42,
    R.drawable.img43,
    R.drawable.img44,
    R.drawable.img45,
    R.drawable.img46,
    R.drawable.img47,
    R.drawable.img48,
    R.drawable.img49,
    R.drawable.img50,
    R.drawable.img51,
    R.drawable.img52,
    R.drawable.img53,
    R.drawable.img54,
    R.drawable.img56,
    R.drawable.img57,
    R.drawable.img59,
    R.drawable.img61,
    R.drawable.img62,
    R.drawable.img63,
    R.drawable.img64,
    R.drawable.img65,
    R.drawable.img66,
    R.drawable.img67,
    R.drawable.img68,
    R.drawable.img69
)

fun checkABC(letter: String): Int {
    var id = 0
    when (letter) {
        "A" -> {
            id = abcId[0]
        }
        "B" -> {
            id = abcId[1]
        }
        "C" -> {
            id = abcId[2]
        }
        "D" -> {
            id = abcId[3]
        }
        "E" -> {
            id = abcId[4]
        }
        "F" -> {
            id = abcId[5]
        }
        "G" -> {
            id = abcId[6]
        }
        "H" -> {
            id = abcId[7]
        }
        "I" -> {
            id = abcId[8]
        }
        "J" -> {
            id = abcId[9]
        }
        "K" -> {
            id = abcId[10]
        }
        "L" -> {
            id = abcId[11]
        }
        "M" -> {
            id = abcId[12]
        }
        "N" -> {
            id = abcId[13]
        }
        "O" -> {
            id = abcId[14]
        }
        "P" -> {
            id = abcId[15]
        }
        "Q" -> {
            id = abcId[16]
        }
        "R" -> {
            id = abcId[17]
        }
        "S" -> {
            id = abcId[18]
        }
        "T" -> {
            id = abcId[19]
        }
        "U" -> {
            id = abcId[20]
        }
        "V" -> {
            id = abcId[21]
        }
        "W" -> {
            id = abcId[22]
        }
        "X" -> {
            id = abcId[23]
        }
        "Y" -> {
            id = abcId[24]
        }
        "Z" -> {
            id = abcId[25]
        }
    }
    return id
}

val abcLetter: ArrayList<String> = arrayListOf(
    "A",
    "B",
    "C",
    "D",
    "E",
    "F",
    "G",
    "H",
    "I",
    "J",
    "K",
    "L",
    "M",
    "N",
    "O",
    "P",
    "Q",
    "R",
    "S",
    "T",
    "U",
    "V",
    "W",
    "X",
    "Y",
    "Z"
)
val images1 = mutableListOf(
    R.drawable.mem1,
    R.drawable.mem2,
    R.drawable.mem3,
    R.drawable.mem4,
    R.drawable.mem5,
    R.drawable.mem6,
    R.drawable.mem1,
    R.drawable.mem2,
    R.drawable.mem3,
    R.drawable.mem4,
    R.drawable.mem5,
    R.drawable.mem6,

    )
val images2 = mutableListOf(
    R.drawable.mem7,
    R.drawable.mem8,
    R.drawable.mem9,
    R.drawable.mem10,
    R.drawable.mem11,
    R.drawable.mem12,
    R.drawable.mem7,
    R.drawable.mem8,
    R.drawable.mem9,
    R.drawable.mem10,
    R.drawable.mem11,
    R.drawable.mem12
)
val images3 = mutableListOf(
    R.drawable.mem13,
    R.drawable.mem14,
    R.drawable.mem15,
    R.drawable.mem16,
    R.drawable.mem17,
    R.drawable.mem18,
    R.drawable.mem13,
    R.drawable.mem14,
    R.drawable.mem15,
    R.drawable.mem16,
    R.drawable.mem17,
    R.drawable.mem18

)
val images4 = mutableListOf(
    R.drawable.mem19,
    R.drawable.mem21,
    R.drawable.mem22,
    R.drawable.mem23,
    R.drawable.mem24,
    R.drawable.mem25,
    R.drawable.mem19,
    R.drawable.mem21,
    R.drawable.mem22,
    R.drawable.mem23,
    R.drawable.mem24,
    R.drawable.mem25

)
val images5 = mutableListOf(
    R.drawable.mem26,
    R.drawable.mem27,
    R.drawable.mem28,
    R.drawable.mem29,
    R.drawable.mem30,
    R.drawable.mem31,
    R.drawable.mem26,
    R.drawable.mem27,
    R.drawable.mem28,
    R.drawable.mem29,
    R.drawable.mem30,
    R.drawable.mem31

)
val images6 = mutableListOf(
    R.drawable.mem32,
    R.drawable.mem33,
    R.drawable.mem34,
    R.drawable.mem35,
    R.drawable.mem36,
    R.drawable.mem37,
    R.drawable.mem32,
    R.drawable.mem33,
    R.drawable.mem34,
    R.drawable.mem35,
    R.drawable.mem36,
    R.drawable.mem37
)
val images7 = mutableListOf(
    R.drawable.mem38,
    R.drawable.mem39,
    R.drawable.mem40,
    R.drawable.mem41,
    R.drawable.mem42,
    R.drawable.mem43,
    R.drawable.mem38,
    R.drawable.mem39,
    R.drawable.mem40,
    R.drawable.mem41,
    R.drawable.mem42,
    R.drawable.mem43
)
val images8 = mutableListOf(
    R.drawable.mem44,
    R.drawable.mem45,
    R.drawable.mem46,
    R.drawable.mem47,
    R.drawable.mem48,
    R.drawable.mem49,
    R.drawable.mem44,
    R.drawable.mem45,
    R.drawable.mem46,
    R.drawable.mem47,
    R.drawable.mem48,
    R.drawable.mem49,
)
val images9 = mutableListOf(
    R.drawable.mem50,
    R.drawable.mem51,
    R.drawable.mem52,
    R.drawable.mem53,
    R.drawable.mem54,
    R.drawable.mem55,
    R.drawable.mem50,
    R.drawable.mem51,
    R.drawable.mem52,
    R.drawable.mem53,
    R.drawable.mem54,
    R.drawable.mem55,
)
val images10 = mutableListOf(
    R.drawable.mem56,
    R.drawable.mem57,
    R.drawable.mem58,
    R.drawable.mem59,
    R.drawable.mem60,
    R.drawable.mem61,
    R.drawable.mem56,
    R.drawable.mem57,
    R.drawable.mem58,
    R.drawable.mem59,
    R.drawable.mem60,
    R.drawable.mem61,
)
val images11 = mutableListOf(
    R.drawable.mem62,
    R.drawable.mem63,
    R.drawable.mem64,
    R.drawable.mem65,
    R.drawable.mem66,
    R.drawable.mem67,
    R.drawable.mem62,
    R.drawable.mem63,
    R.drawable.mem64,
    R.drawable.mem65,
    R.drawable.mem66,
    R.drawable.mem67
)
