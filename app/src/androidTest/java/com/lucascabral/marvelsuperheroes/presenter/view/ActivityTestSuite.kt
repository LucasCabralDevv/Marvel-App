package com.lucascabral.marvelsuperheroes.presenter.view

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    SplashScreenActivityTest::class,
    AllCharactersActivityTest::class,
    CharacterDetailsActivityTest::class,
    MarvelYoutubeActivityTest::class
)
class ActivityTestSuite
