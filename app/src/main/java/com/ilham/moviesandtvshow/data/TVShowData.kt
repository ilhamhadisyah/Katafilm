package com.ilham.moviesandtvshow.data

import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.data.model.Movie
import com.ilham.moviesandtvshow.data.model.TVShow

object TVShowData {
    private val tvTitle = arrayOf(
        "Arrow",
        "Doom Patrol",
        "Dragon Ball",
        "Fairytail",
        "Family Guy",
        "Flash",
        "God",
        "Gotham",
        "Grey Anatomy",
        "Hanna",
        "Iron Fist",
        "Naruto Shipudden",
        "NCIS",
        "Riverdale",
        "Shameless",
        "Super-Girl",
        "Super-Natural",
        "The Simpson",
        "The Umbrella"
    )
    private val tvPoster = intArrayOf(
        R.drawable.poster_arrow,
        R.drawable.poster_doom_patrol,
        R.drawable.poster_dragon_ball,
        R.drawable.poster_fairytail,
        R.drawable.poster_family_guy,
        R.drawable.poster_flash,
        R.drawable.poster_god,
        R.drawable.poster_gotham,
        R.drawable.poster_grey_anatomy,
        R.drawable.poster_hanna,
        R.drawable.poster_iron_fist,
        R.drawable.poster_naruto_shipudden,
        R.drawable.poster_ncis,
        R.drawable.poster_riverdale,
        R.drawable.poster_shameless,
        R.drawable.poster_supergirl,
        R.drawable.poster_supernatural,
        R.drawable.poster_the_simpson,
        R.drawable.poster_the_umbrella
    )
    private val tvPopularity = doubleArrayOf(
        7.5,
        7.2,
        6.9,
        8.0,
        5.7,
        7.4,
        6.9,
        6.7,
        7.8,
        8.3,
        6.6,
        5.9,
        6.1,
        6.7,
        7.3,
        5.9,
        5.4,
        7.2,
        6.9
    )
    private val releaseYear = intArrayOf(
        2018,
        2019,
        2018,
        2018,
        2019,
        2015,
        2018,
        2019,
        2019,
        2018,
        2018,
        2018,
        2018,
        2018,
        2018,
        2012,
        2018,
        2019,
        2002,
        2013
    )
    private val originalLanguage = arrayOf(
        "US",
        "US",
        "US",
        "US",
        "US",
        "US",
        "US",
        "US",
        "US",
        "US",
        "US",
        "TW",
        "US",
        "US",
        "US",
        "US",
        "US",
        "ID",
        "RU"
    )
    private val tvOverview = arrayOf(
        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
        "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
        "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
        "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
        "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
        "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
        "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
        "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
        "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
        "Wreck-It Ralph is the 9-foot-tall, 643-pound villain of an arcade video game named Fix-It Felix Jr., in which the game's titular hero fixes buildings that Ralph destroys. Wanting to prove he can be a good guy and not just a villain, Ralph escapes his game and lands in Hero's Duty, a first-person shooter where he helps the game's hero battle against alien invaders. He later enters Sugar Rush, a kart racing game set on tracks made of candies, cookies and other sweets. There, Ralph meets Vanellope von Schweetz who has learned that her game is faced with a dire threat that could affect the entire arcade, and one that Ralph may have inadvertently started.",
        "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
        "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
        "After being bitten by a genetically altered spider, nerdy high school student Peter Parker is endowed with amazing powers to become the Amazing superhero known as Spider-Man.",
        "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles."
    )
    private val movieGenre = arrayOf(
        "Romance",
        "Action",
        "Fantasy",
        "Music",
        "Action",
        "Drama",
        "Adventure",
        "Action",
        "Animation",
        "Science Fiction",
        "Action",
        "Science Fiction",
        "Action",
        "Horror",
        "Family",
        "Thriller",
        "Mystery",
        "Fantasy",
        "War"
    )
    private val age =
        arrayOf(18, "PG-13", "PG-13", "PG-13", "PG-13", "PG-13", "PG-13", "13", "PG-13", "PG-13", "PG-13", "PG-13", "PG-13", "R", "PG", "PG-13", "R", "PG-13", "12")
    val listTV: ArrayList<TVShow>
        get() {
            val list = arrayListOf<TVShow>()
            for (position in tvTitle.indices) {
                val data = TVShow()
                data.title = tvTitle[position]
                data.poster = tvPoster[position]
                data.originalLang = originalLanguage[position]
                data.score = tvPopularity[position]
                data.overView = tvOverview[position]
                data.genre = movieGenre[position]
                data.age = age[position].toString()
                data.releaseYear = releaseYear[position].toString()
                list.add(data)
            }
            return list
        }

    fun getTvDetail(position : Int):TVShow{
        return listTV[position]
    }
}