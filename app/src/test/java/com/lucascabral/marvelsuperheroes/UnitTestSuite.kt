package com.lucascabral.marvelsuperheroes

import com.lucascabral.marvelsuperheroes.domain.GetVideosTest
import com.lucascabral.marvelsuperheroes.presenter.viewmodel.MarvelYoutubeViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetVideosTest::class,
    MarvelYoutubeViewModelTest::class
)
class UnitTestSuite