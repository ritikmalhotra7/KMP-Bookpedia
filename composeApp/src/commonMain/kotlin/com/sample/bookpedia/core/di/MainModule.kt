package com.sample.bookpedia.core.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.sample.bookpedia.core.data.remote.HttpClientFactory
import com.sample.bookpedia.feat_book.data.database.DatabaseFactory
import com.sample.bookpedia.feat_book.data.database.FavouriteBookDatabase
import com.sample.bookpedia.feat_book.data.remote.KtorRemoteBookDataSource
import com.sample.bookpedia.feat_book.data.remote.RemoteBookDataSource
import com.sample.bookpedia.feat_book.data.repository.DefaultBookRepository
import com.sample.bookpedia.feat_book.domain.repository.BookRepository
import com.sample.bookpedia.feat_book.presentation.book_details.BookDetailsViewModel
import com.sample.bookpedia.feat_book.presentation.book_graph.SharedBookViewModel
import com.sample.bookpedia.feat_book.presentation.book_list.BookListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module {
    single{
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single{
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single{
        get<FavouriteBookDatabase>().bookDao()
    }

    viewModelOf(::SharedBookViewModel)
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailsViewModel)
}