package com.example.moviecatalogue.core.utils

import com.example.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowEntity

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                1,
                "A Star Is Born",
                "2018",
                75.0,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                2,
                "Alita: Battle Angel",
                "2019",
                72.0,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                3,
                "Aquaman",
                "2018",
                69.0,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                4,
                "Bohemian Rhapsody",
                "2018",
                80.0,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                5,
                "Cold Pursuit",
                "2019",
                57.0,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                6,
                "Creed II",
                "2018",
                69.0,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                7,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "2018",
                69.0,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                8,
                "Glass",
                "2019",
                67.0,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                9,
                "How to Train Your Dragon: The Hidden World",
                "2019",
                78.0,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                ""
            )
        )
        movies.add(
            MovieEntity(
                10,
                "Avengers: Infinity War",
                "2018",
                83.0,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                ""
            )
        )
        return movies
    }

    fun generateDummyTvShows(): ArrayList<TvShowEntity> {
        val shows = ArrayList<TvShowEntity>()
        shows.add(
            TvShowEntity(
                1,
                "Arrow",
                "2012",
                66.0,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                2,
                "Doom Patrol",
                "2019",
                76.0,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                3,
                "Dragon Ball",
                "1986",
                81.0,
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                4,
                "Fairy Tail",
                "2009",
                78.0,
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                5,
                "Family Guy",
                "1999",
                70.0,
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                6,
                "The Flash",
                "2014",
                77.0,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only meta-human who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                7,
                "Gotham",
                "2014",
                75.0,
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                8,
                "Grey's Anatomy",
                "2005",
                82.0,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                9,
                "Hanna",
                "2019",
                75.0,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                ""
            )
        )
        shows.add(
            TvShowEntity(
                10,
                "Marvel's Iron Fist",
                "2017",
                66.0,
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                ""
            )
        )
        return shows
    }
}