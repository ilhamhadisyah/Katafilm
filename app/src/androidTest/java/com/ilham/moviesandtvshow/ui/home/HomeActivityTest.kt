package com.ilham.moviesandtvshow.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ilham.moviesandtvshow.R
import com.ilham.moviesandtvshow.utils.EspressoIdlingRes
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingRes.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingRes.countingIdlingResource)
    }


    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)


    @Test
    fun tabMovingTestAndScrollTest() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )

        onView(withId(R.id.liked_list)).perform(click())
        onView(withId(R.id.movie)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )

    }

    @Test
    fun openMovieDetail() {
        onView(withId(R.id.movie)).perform(click())
        onView(withText("Now Playing")).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.content_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_image)).check(matches(isDisplayed()))
        onView(withId(R.id.year_release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()))
        pressBack()
    }

    @Test
    fun openTvDetail() {
        onView(withId(R.id.tv_show)).perform(click())
        onView(withText("Popular")).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.content_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_image)).check(matches(isDisplayed()))
        onView(withId(R.id.year_release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()))
        pressBack()
    }


    @Test
    fun addMovieToLike(){
        onView(withId(R.id.movie)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_to_like)).perform(click())
    }

    @Test
    fun addTvShowToLike(){
        onView(withId(R.id.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_to_like)).perform(click())
    }

    @Test
    fun openLikedList() {
        onView(withId(R.id.liked_list)).perform(click())

        onView(withId(R.id.menu_spinner)).perform(click())
        onView(withText("Movie")).perform(click())
        onView(withId(R.id.rv_liked_list)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_spinner)).perform(click())
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_liked_list)).check(matches(isDisplayed()))
    }

    @Test
    fun deleteItemInMovieLikedList(){
        onView(withId(R.id.movie)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.add_to_like)).perform(click())
        pressBack()
        onView(withId(R.id.liked_list)).perform(click())

        onView(withId(R.id.menu_spinner)).perform(click())
        onView(withText("Movie")).perform(click())
        onView(withId(R.id.rv_liked_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_to_like)).perform(click())
        pressBack()
    }

    @Test
    fun deleteItemInTvShowLikedList(){
        onView(withId(R.id.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.add_to_like)).perform(click())
        pressBack()
        onView(withId(R.id.liked_list)).perform(click())

        onView(withId(R.id.menu_spinner)).perform(click())
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_liked_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_to_like)).perform(click())
        pressBack()
    }


}