package com.elypia.elypiai.test;

import com.elypia.elypiai.myanimelist.Anime;
import com.elypia.elypiai.myanimelist.data.AnimeType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyAnimeListTest {

    @Test
    public void testAnime() {
        String xml = "<anime><entry><id>71</id><title>Full Metal Panic!</title><english>Full Metal Panic!</english><synonyms>FMP; Fullmetal Panic!</synonyms><episodes>24</episodes><score>7.76</score><type>TV</type><status>Finished Airing</status><start_date>2002-01-08</start_date><end_date>2002-06-18</end_date><synopsis>Equipped with cutting-edge weaponry and specialized troops, a private military organization named Mithril strives to extinguish the world&#039;s terrorism and all threats to peace on earth. The organization is powered by the &quot;Whispered,&quot; individuals who possess intuitive knowledge and the remarkable ability to create powerful devices and machinery.<br /> <br /> Seventeen-year-old Sousuke Sagara, a sergeant working for Mithril, has been assigned to protect Kaname Chidori, a Whispered candidate. He is ordered to join her high school class and be as close to her as possible to prevent her from falling into enemy hands&mdash;that is, if he can safely blend in with their fellow classmates without revealing his true identity.<br /> <br /> Sousuke, who was raised on a battlefield and has very little knowledge of an average high school student&#039;s lifestyle, must adapt to a normal school life to safeguard Kaname. However, enemy forces have already begun making their move, and Sousuke is about to find out that the adversary coming for the Whispered girl may be a lot more familiar than he expects.<br /> <br /> [Written by MAL Rewrite]</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/2/75259.jpg</image></entry><entry><id>72</id><title>Full Metal Panic? Fumoffu</title><english>Full Metal Panic? Fumoffu</english><synonyms>Full Metal Panic Fumoffu; Fullmetal Panic? Fumoffu</synonyms><episodes>12</episodes><score>8.16</score><type>TV</type><status>Finished Airing</status><start_date>2003-08-26</start_date><end_date>2003-10-18</end_date><synopsis>It's back-to-school mayhem with Kaname Chidori and her war-freak classmate Sousuke Sagara as they encounter more misadventures in and out of Jindai High School. But when Kaname gets into some serious trouble, Sousuke takes the guise of Bonta-kun&mdash;the gun-wielding, butt-kicking mascot. And while he struggles to continue living as a normal teenager, Sousuke also has to deal with protecting his superior officer Teletha Testarossa, who has decided to take a vacation from Mithril and spend a couple of weeks as his and Kaname's classmate.<br /> <br /> (Source: ANN)</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/4/75260.jpg</image></entry><entry><id>73</id><title>Full Metal Panic! The Second Raid</title><english>Full Metal Panic! The Second Raid</english><synonyms>Full Metal Panic! TSR</synonyms><episodes>13</episodes><score>8.03</score><type>TV</type><status>Finished Airing</status><start_date>2005-07-14</start_date><end_date>2005-10-20</end_date><synopsis>This series is set about two months after the events ocurred in the Tuatha de Danaan at the end of the original series. Mithril becomes aware of a secret organization that has technology able to counter the ECS (Electronic Cloaking System) mode. This organization, known as Amalgam, also has &quot;Black Technology,&quot; obtained from &quot;Whispered&quot; like Kaname Chidori, and like the other intelligence agencies, they intend to obtain more; however, when Sousuke&#039;s mission to protect Chidori is terminated by Mithril, all seems to be in place for Amalgam&#039;s plans...<br /> <br /> (Source: ANN)</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/11/75261.jpg</image></entry><entry><id>121</id><title>Fullmetal Alchemist</title><english>Fullmetal Alchemist</english><synonyms>Hagane no Renkinjutsushi; FMA; Full Metal Alchemist</synonyms><episodes>51</episodes><score>8.29</score><type>TV</type><status>Finished Airing</status><start_date>2003-10-04</start_date><end_date>2004-10-02</end_date><synopsis>Edward Elric, a young, brilliant alchemist, has lost much in his twelve-year life: when he and his brother Alphonse try to resurrect their dead mother through the forbidden act of human transmutation, Edward loses his brother as well as two of his limbs. With his supreme alchemy skills, Edward binds Alphonse&#039;s soul to a large suit of armor.<br /> <br /> A year later, Edward, now promoted to the fullmetal alchemist of the state, embarks on a journey with his younger brother to obtain the Philosopher&#039;s Stone. The fabled mythical object is rumored to be capable of amplifying an alchemist&#039;s abilities by leaps and bounds, thus allowing them to override the fundamental law of alchemy: to gain something, an alchemist must sacrifice something of equal value. Edward hopes to draw into the military&#039;s resources to find the fabled stone and restore his and Alphonse&#039;s bodies to normal. However, the Elric brothers soon discover that there is more to the legendary stone than meets the eye, as they are led to the epicenter of a far darker battle than they could have ever imagined.<br /> <br /> [Written by MAL Rewrite]</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/10/75815.jpg</image></entry><entry><id>1015</id><title>Full Metal Panic! The Second Raid: Wari to Hima na Sentaichou no Ichinichi</title><english/><synonyms>The Commanding Officer's Rather Quiet Day; Full Metal Panic! The Second Raid Special; Full Metal Panic! The Second Raid OVA</synonyms><episodes>1</episodes><score>7.75</score><type>Special</type><status>Finished Airing</status><start_date>2006-05-26</start_date><end_date>2006-05-26</end_date><synopsis>On her day off, Tessa wakes up in her commander chair. After regaining her composure, she notices that her favorite stuffed animal is missing and thus tries to remember what actually transpired the night before. To do so, she will spend time with all the main characters of the Danaan crew, and eventually recalls the events of the previous night.<br /> <br /> [Written by MAL Rewrite]</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/3/23458.jpg</image></entry><entry><id>2601</id><title>Juusenki L-Gaim III: Full Metal Soldier</title><english/><synonyms>Heavy Metal L-Gaim OVA; Heavy Metal L-Gaim III: Full Metal Soldier</synonyms><episodes>1</episodes><score>6.40</score><type>OVA</type><status>Finished Airing</status><start_date>1987-03-28</start_date><end_date>1987-03-28</end_date><synopsis>A side story taking place somewhere before the second half of the series, prior to Daba acquiring the L-Gaim Mk-II. Daba's comrade Leccee is captured by Poseidal's forces, and Daba and his rebels attempt to save her.</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/3/24615.jpg</image></entry><entry><id>6291</id><title>Full Metal Panic! The Second Raid Episode 000</title><english/><synonyms>Full Metal Panic! The Second Raid Pre-festival: Night of the Light Novel</synonyms><episodes>1</episodes><score>7.52</score><type>Special</type><status>Finished Airing</status><start_date>2005-07-06</start_date><end_date>2005-07-06</end_date><synopsis>Sousuke, Kurz and Melissa are deployed to the Republic of Manistan in Central Asia to eradicate a growing civil war between rebels and the Manistani military. <br /> <br /> (Source: Wikipedia)</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/9/45048.jpg</image></entry><entry><id>31931</id><title>Full Metal Panic! Invisible Victory</title><english/><synonyms>Full Metal Panic! IV</synonyms><episodes>0</episodes><score>0.00</score><type>TV</type><status>Not yet aired</status><start_date>2018-04-13</start_date><end_date>0000-00-00</end_date><synopsis/><image>https://myanimelist.cdn-dena.com/images/anime/9/88584.jpg</image></entry><entry><id>36342</id><title>Full Metal Panic! Movie 1</title><english/><synonyms>FMP</synonyms><episodes>1</episodes><score>0.00</score><type>Movie</type><status>Finished Airing</status><start_date>2017-11-25</start_date><end_date>2017-11-25</end_date><synopsis>Shikidouji, the illustrator of Shoji Gatoh&#039;s [i]Full Metal Panic![/i] light novel series, revealed that production has been green-lit on a &quot;director&#039;s cut&quot; version of the first [i]Full Metal Panic!![/i] television anime series from 2002. The director&#039;s cut will consist of three films. The announcement does not state if the film trilogy will add new footage. <br /> <br /> (Source: ANN)</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/6/88543.jpg</image></entry><entry><id>36343</id><title>Full Metal Panic! Movie 2</title><english/><synonyms>FMP</synonyms><episodes>1</episodes><score>0.00</score><type>Movie</type><status>Finished Airing</status><start_date>2018-01-13</start_date><end_date>2018-01-13</end_date><synopsis>Shikidouji, the illustrator of Shoji Gatoh&#039;s [i]Full Metal Panic![/i] light novel series, revealed that production has been green-lit on a &quot;director&#039;s cut&quot; version of the first [i]Full Metal Panic!![/i] television anime series from 2002. The director&#039;s cut will consist of three films. The announcement does not state if the film trilogy will add new footage. <br /> <br /> (Source: ANN)</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/4/89498.jpg</image></entry><entry><id>36344</id><title>Full Metal Panic! Movie 3</title><english/><synonyms>FMP</synonyms><episodes>1</episodes><score>0.00</score><type>Movie</type><status>Finished Airing</status><start_date>2018-01-20</start_date><end_date>2018-01-20</end_date><synopsis>Shikidouji, the illustrator of Shoji Gatoh&#039;s [i]Full Metal Panic![/i] light novel series, revealed that production has been green-lit on a &quot;director&#039;s cut&quot; version of the first [i]Full Metal Panic!![/i] television anime series from 2002. The director&#039;s cut will consist of three films. The announcement does not state if the film trilogy will add new footage. <br /> <br /> (Source: ANN)</synopsis><image>https://myanimelist.cdn-dena.com/images/anime/12/89870.jpg</image></entry></anime>";
        Document document = Jsoup.parse(xml, "", Parser.xmlParser());
        Anime anime = new Anime(document);

        assertAll("Verify Parsing Anime Data Correctly",
            () -> assertEquals(71, anime.getId()),
            () -> assertEquals("Full Metal Panic!", anime.getTitle()),
            () -> assertEquals(24, anime.getEpisodes()),
            () -> assertEquals(7.76, anime.getScore()),
            () -> assertEquals(AnimeType.TV, anime.getType()),
            () -> assertEquals("https://myanimelist.cdn-dena.com/images/anime/2/75259.jpg", anime.getImageUrl())
        );
    }
}
