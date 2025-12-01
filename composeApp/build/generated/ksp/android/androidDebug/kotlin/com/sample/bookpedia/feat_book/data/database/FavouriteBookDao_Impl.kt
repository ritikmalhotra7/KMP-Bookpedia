package com.sample.bookpedia.feat_book.`data`.database

import androidx.collection.ArrayMap
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchArrayMap
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.sample.bookpedia.feat_book.`data`.database.entities.AuthorEntity
import com.sample.bookpedia.feat_book.`data`.database.entities.BookAuthorCrossRef
import com.sample.bookpedia.feat_book.`data`.database.entities.BookEntity
import com.sample.bookpedia.feat_book.`data`.database.entities.BookLanguageCrossRef
import com.sample.bookpedia.feat_book.`data`.database.entities.LanguageEntity
import com.sample.bookpedia.feat_book.`data`.database.relations.FullBookDetails
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavouriteBookDao_Impl(
  __db: RoomDatabase,
) : FavouriteBookDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfLanguageEntity: EntityInsertAdapter<LanguageEntity>

  private val __insertAdapterOfBookLanguageCrossRef: EntityInsertAdapter<BookLanguageCrossRef>

  private val __insertAdapterOfAuthorEntity: EntityInsertAdapter<AuthorEntity>

  private val __insertAdapterOfBookAuthorCrossRef: EntityInsertAdapter<BookAuthorCrossRef>

  private val __upsertAdapterOfBookEntity: EntityUpsertAdapter<BookEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfLanguageEntity = object : EntityInsertAdapter<LanguageEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR IGNORE INTO `language_table` (`id`,`name`) VALUES (nullif(?, 0),?)"

      protected override fun bind(statement: SQLiteStatement, entity: LanguageEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
      }
    }
    this.__insertAdapterOfBookLanguageCrossRef = object :
        EntityInsertAdapter<BookLanguageCrossRef>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `BookLanguageCrossRef` (`bookId`,`languageId`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookLanguageCrossRef) {
        statement.bindText(1, entity.bookId)
        statement.bindLong(2, entity.languageId.toLong())
      }
    }
    this.__insertAdapterOfAuthorEntity = object : EntityInsertAdapter<AuthorEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR IGNORE INTO `author_table` (`id`,`authorName`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AuthorEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.authorName)
      }
    }
    this.__insertAdapterOfBookAuthorCrossRef = object : EntityInsertAdapter<BookAuthorCrossRef>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `BookAuthorCrossRef` (`bookId`,`authorId`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookAuthorCrossRef) {
        statement.bindText(1, entity.bookId)
        statement.bindLong(2, entity.authorId.toLong())
      }
    }
    this.__upsertAdapterOfBookEntity = EntityUpsertAdapter<BookEntity>(object :
        EntityInsertAdapter<BookEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `book_table` (`id`,`title`,`description`,`imageUrl`,`firstPublishYear`,`ratingAverage`,`ratingCount`,`numPagesMedian`,`numEditions`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpDescription)
        }
        statement.bindText(4, entity.imageUrl)
        val _tmpFirstPublishYear: String? = entity.firstPublishYear
        if (_tmpFirstPublishYear == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpFirstPublishYear)
        }
        val _tmpRatingAverage: Double? = entity.ratingAverage
        if (_tmpRatingAverage == null) {
          statement.bindNull(6)
        } else {
          statement.bindDouble(6, _tmpRatingAverage)
        }
        val _tmpRatingCount: Int? = entity.ratingCount
        if (_tmpRatingCount == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpRatingCount.toLong())
        }
        val _tmpNumPagesMedian: Int? = entity.numPagesMedian
        if (_tmpNumPagesMedian == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpNumPagesMedian.toLong())
        }
        val _tmpNumEditions: Int? = entity.numEditions
        if (_tmpNumEditions == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpNumEditions.toLong())
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<BookEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `book_table` SET `id` = ?,`title` = ?,`description` = ?,`imageUrl` = ?,`firstPublishYear` = ?,`ratingAverage` = ?,`ratingCount` = ?,`numPagesMedian` = ?,`numEditions` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BookEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpDescription)
        }
        statement.bindText(4, entity.imageUrl)
        val _tmpFirstPublishYear: String? = entity.firstPublishYear
        if (_tmpFirstPublishYear == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpFirstPublishYear)
        }
        val _tmpRatingAverage: Double? = entity.ratingAverage
        if (_tmpRatingAverage == null) {
          statement.bindNull(6)
        } else {
          statement.bindDouble(6, _tmpRatingAverage)
        }
        val _tmpRatingCount: Int? = entity.ratingCount
        if (_tmpRatingCount == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpRatingCount.toLong())
        }
        val _tmpNumPagesMedian: Int? = entity.numPagesMedian
        if (_tmpNumPagesMedian == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpNumPagesMedian.toLong())
        }
        val _tmpNumEditions: Int? = entity.numEditions
        if (_tmpNumEditions == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpNumEditions.toLong())
        }
        statement.bindText(10, entity.id)
      }
    })
  }

  public override suspend fun insertLanguages(languages: List<LanguageEntity>): List<Long> =
      performSuspending(__db, false, true) { _connection ->
    val _result: List<Long> = __insertAdapterOfLanguageEntity.insertAndReturnIdsList(_connection,
        languages)
    _result
  }

  public override suspend fun insertBookLanguageCrossRefs(crossRefs: List<BookLanguageCrossRef>):
      Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBookLanguageCrossRef.insert(_connection, crossRefs)
  }

  public override suspend fun insertAuthors(authors: List<AuthorEntity>): List<Long> =
      performSuspending(__db, false, true) { _connection ->
    val _result: List<Long> = __insertAdapterOfAuthorEntity.insertAndReturnIdsList(_connection,
        authors)
    _result
  }

  public override suspend fun insertBookAuthorCrossRefs(crossRefs: List<BookAuthorCrossRef>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBookAuthorCrossRef.insert(_connection, crossRefs)
  }

  public override suspend fun upsertBookWithRelations(
    book: BookEntity,
    languages: List<LanguageEntity>,
    authors: List<AuthorEntity>,
  ): Unit = performInTransactionSuspending(__db) {
    super@FavouriteBookDao_Impl.upsertBookWithRelations(book, languages, authors)
  }

  public override suspend fun insertBook(book: BookEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __upsertAdapterOfBookEntity.upsert(_connection, book)
  }

  public override fun getFavouriteBooks(): Flow<List<FullBookDetails>> {
    val _sql: String = "SELECT * FROM book_table"
    return createFlow(__db, true, arrayOf("BookLanguageCrossRef", "language_table",
        "BookAuthorCrossRef", "author_table", "book_table")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _cursorIndexOfFirstPublishYear: Int = getColumnIndexOrThrow(_stmt, "firstPublishYear")
        val _cursorIndexOfRatingAverage: Int = getColumnIndexOrThrow(_stmt, "ratingAverage")
        val _cursorIndexOfRatingCount: Int = getColumnIndexOrThrow(_stmt, "ratingCount")
        val _cursorIndexOfNumPagesMedian: Int = getColumnIndexOrThrow(_stmt, "numPagesMedian")
        val _cursorIndexOfNumEditions: Int = getColumnIndexOrThrow(_stmt, "numEditions")
        val _collectionLanguages: ArrayMap<String, MutableList<LanguageEntity>> =
            ArrayMap<String, MutableList<LanguageEntity>>()
        val _collectionAuthors: ArrayMap<String, MutableList<AuthorEntity>> =
            ArrayMap<String, MutableList<AuthorEntity>>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_cursorIndexOfId)
          if (!_collectionLanguages.containsKey(_tmpKey)) {
            _collectionLanguages.put(_tmpKey, mutableListOf())
          }
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_cursorIndexOfId)
          if (!_collectionAuthors.containsKey(_tmpKey_1)) {
            _collectionAuthors.put(_tmpKey_1, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshiplanguageTableAscomSampleBookpediaFeatBookDataDatabaseEntitiesLanguageEntity(_connection,
            _collectionLanguages)
        __fetchRelationshipauthorTableAscomSampleBookpediaFeatBookDataDatabaseEntitiesAuthorEntity(_connection,
            _collectionAuthors)
        val _result: MutableList<FullBookDetails> = mutableListOf()
        while (_stmt.step()) {
          val _item: FullBookDetails
          val _tmpBook: BookEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_cursorIndexOfTitle)
          val _tmpDescription: String?
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          }
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_cursorIndexOfImageUrl)
          val _tmpFirstPublishYear: String?
          if (_stmt.isNull(_cursorIndexOfFirstPublishYear)) {
            _tmpFirstPublishYear = null
          } else {
            _tmpFirstPublishYear = _stmt.getText(_cursorIndexOfFirstPublishYear)
          }
          val _tmpRatingAverage: Double?
          if (_stmt.isNull(_cursorIndexOfRatingAverage)) {
            _tmpRatingAverage = null
          } else {
            _tmpRatingAverage = _stmt.getDouble(_cursorIndexOfRatingAverage)
          }
          val _tmpRatingCount: Int?
          if (_stmt.isNull(_cursorIndexOfRatingCount)) {
            _tmpRatingCount = null
          } else {
            _tmpRatingCount = _stmt.getLong(_cursorIndexOfRatingCount).toInt()
          }
          val _tmpNumPagesMedian: Int?
          if (_stmt.isNull(_cursorIndexOfNumPagesMedian)) {
            _tmpNumPagesMedian = null
          } else {
            _tmpNumPagesMedian = _stmt.getLong(_cursorIndexOfNumPagesMedian).toInt()
          }
          val _tmpNumEditions: Int?
          if (_stmt.isNull(_cursorIndexOfNumEditions)) {
            _tmpNumEditions = null
          } else {
            _tmpNumEditions = _stmt.getLong(_cursorIndexOfNumEditions).toInt()
          }
          _tmpBook =
              BookEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpImageUrl,_tmpFirstPublishYear,_tmpRatingAverage,_tmpRatingCount,_tmpNumPagesMedian,_tmpNumEditions)
          val _tmpLanguagesCollection: MutableList<LanguageEntity>
          val _tmpKey_2: String
          _tmpKey_2 = _stmt.getText(_cursorIndexOfId)
          _tmpLanguagesCollection = _collectionLanguages.getValue(_tmpKey_2)
          val _tmpAuthorsCollection: MutableList<AuthorEntity>
          val _tmpKey_3: String
          _tmpKey_3 = _stmt.getText(_cursorIndexOfId)
          _tmpAuthorsCollection = _collectionAuthors.getValue(_tmpKey_3)
          _item = FullBookDetails(_tmpBook,_tmpLanguagesCollection,_tmpAuthorsCollection)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFavouriteBook(bookId: String): BookEntity? {
    val _sql: String = "SELECT * from book_table WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, bookId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _cursorIndexOfFirstPublishYear: Int = getColumnIndexOrThrow(_stmt, "firstPublishYear")
        val _cursorIndexOfRatingAverage: Int = getColumnIndexOrThrow(_stmt, "ratingAverage")
        val _cursorIndexOfRatingCount: Int = getColumnIndexOrThrow(_stmt, "ratingCount")
        val _cursorIndexOfNumPagesMedian: Int = getColumnIndexOrThrow(_stmt, "numPagesMedian")
        val _cursorIndexOfNumEditions: Int = getColumnIndexOrThrow(_stmt, "numEditions")
        val _result: BookEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_cursorIndexOfTitle)
          val _tmpDescription: String?
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          }
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_cursorIndexOfImageUrl)
          val _tmpFirstPublishYear: String?
          if (_stmt.isNull(_cursorIndexOfFirstPublishYear)) {
            _tmpFirstPublishYear = null
          } else {
            _tmpFirstPublishYear = _stmt.getText(_cursorIndexOfFirstPublishYear)
          }
          val _tmpRatingAverage: Double?
          if (_stmt.isNull(_cursorIndexOfRatingAverage)) {
            _tmpRatingAverage = null
          } else {
            _tmpRatingAverage = _stmt.getDouble(_cursorIndexOfRatingAverage)
          }
          val _tmpRatingCount: Int?
          if (_stmt.isNull(_cursorIndexOfRatingCount)) {
            _tmpRatingCount = null
          } else {
            _tmpRatingCount = _stmt.getLong(_cursorIndexOfRatingCount).toInt()
          }
          val _tmpNumPagesMedian: Int?
          if (_stmt.isNull(_cursorIndexOfNumPagesMedian)) {
            _tmpNumPagesMedian = null
          } else {
            _tmpNumPagesMedian = _stmt.getLong(_cursorIndexOfNumPagesMedian).toInt()
          }
          val _tmpNumEditions: Int?
          if (_stmt.isNull(_cursorIndexOfNumEditions)) {
            _tmpNumEditions = null
          } else {
            _tmpNumEditions = _stmt.getLong(_cursorIndexOfNumEditions).toInt()
          }
          _result =
              BookEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpImageUrl,_tmpFirstPublishYear,_tmpRatingAverage,_tmpRatingCount,_tmpNumPagesMedian,_tmpNumEditions)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLanguages(): List<LanguageEntity> {
    val _sql: String = "SELECT * FROM language_table"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _result: MutableList<LanguageEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: LanguageEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          _item = LanguageEntity(_tmpId,_tmpName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAuthors(): List<AuthorEntity> {
    val _sql: String = "SELECT * FROM author_table"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _result: MutableList<AuthorEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AuthorEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          _item = AuthorEntity(_tmpId,_tmpAuthorName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteFavouriteBook(bookId: String): Int {
    val _sql: String = "DELETE from book_table WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, bookId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  private
      fun __fetchRelationshiplanguageTableAscomSampleBookpediaFeatBookDataDatabaseEntitiesLanguageEntity(_connection: SQLiteConnection,
      _map: ArrayMap<String, MutableList<LanguageEntity>>) {
    val __mapKeySet: Set<String> = _map.keys
    if (__mapKeySet.isEmpty()) {
      return
    }
    if (_map.size > 999) {
      recursiveFetchArrayMap(_map, true) { _tmpMap ->
        __fetchRelationshiplanguageTableAscomSampleBookpediaFeatBookDataDatabaseEntitiesLanguageEntity(_connection,
            _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `language_table`.`id` AS `id`,`language_table`.`name` AS `name`,_junction.`bookId` FROM `BookLanguageCrossRef` AS _junction INNER JOIN `language_table` ON (_junction.`languageId` = `language_table`.`id`) WHERE _junction.`bookId` IN (")
    val _inputSize: Int = __mapKeySet.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (_item: String in __mapKeySet) {
      _stmt.bindText(_argIndex, _item)
      _argIndex++
    }
    try {
      // _junction.bookId
      val _itemKeyIndex: Int = 2
      if (_itemKeyIndex == -1) {
        return
      }
      val _cursorIndexOfId: Int = 0
      val _cursorIndexOfName: Int = 1
      while (_stmt.step()) {
        val _tmpKey: String
        _tmpKey = _stmt.getText(_itemKeyIndex)
        val _tmpRelation: MutableList<LanguageEntity>? = _map.get(_tmpKey)
        if (_tmpRelation != null) {
          val _item_1: LanguageEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          _item_1 = LanguageEntity(_tmpId,_tmpName)
          _tmpRelation.add(_item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  private
      fun __fetchRelationshipauthorTableAscomSampleBookpediaFeatBookDataDatabaseEntitiesAuthorEntity(_connection: SQLiteConnection,
      _map: ArrayMap<String, MutableList<AuthorEntity>>) {
    val __mapKeySet: Set<String> = _map.keys
    if (__mapKeySet.isEmpty()) {
      return
    }
    if (_map.size > 999) {
      recursiveFetchArrayMap(_map, true) { _tmpMap ->
        __fetchRelationshipauthorTableAscomSampleBookpediaFeatBookDataDatabaseEntitiesAuthorEntity(_connection,
            _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `author_table`.`id` AS `id`,`author_table`.`authorName` AS `authorName`,_junction.`bookId` FROM `BookAuthorCrossRef` AS _junction INNER JOIN `author_table` ON (_junction.`authorId` = `author_table`.`id`) WHERE _junction.`bookId` IN (")
    val _inputSize: Int = __mapKeySet.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (_item: String in __mapKeySet) {
      _stmt.bindText(_argIndex, _item)
      _argIndex++
    }
    try {
      // _junction.bookId
      val _itemKeyIndex: Int = 2
      if (_itemKeyIndex == -1) {
        return
      }
      val _cursorIndexOfId: Int = 0
      val _cursorIndexOfAuthorName: Int = 1
      while (_stmt.step()) {
        val _tmpKey: String
        _tmpKey = _stmt.getText(_itemKeyIndex)
        val _tmpRelation: MutableList<AuthorEntity>? = _map.get(_tmpKey)
        if (_tmpRelation != null) {
          val _item_1: AuthorEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          _item_1 = AuthorEntity(_tmpId,_tmpAuthorName)
          _tmpRelation.add(_item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
