package com.sample.bookpedia.feat_book.`data`.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavouriteBookDatabase_Impl : FavouriteBookDatabase() {
  private val _favouriteBookDao: Lazy<FavouriteBookDao> = lazy {
    FavouriteBookDao_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "98af7f8ad46a8a3e7c70262b23af378e", "8e020055802ce637172950f690a5cc39") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `book_table` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `imageUrl` TEXT NOT NULL, `firstPublishYear` TEXT, `ratingAverage` REAL, `ratingCount` INTEGER, `numPagesMedian` INTEGER, `numEditions` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `language_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `author_table` (`id` INTEGER NOT NULL, `authorName` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `BookLanguageCrossRef` (`bookId` TEXT NOT NULL, `languageId` INTEGER NOT NULL, PRIMARY KEY(`bookId`, `languageId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `BookAuthorCrossRef` (`bookId` TEXT NOT NULL, `authorId` INTEGER NOT NULL, PRIMARY KEY(`bookId`, `authorId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '98af7f8ad46a8a3e7c70262b23af378e')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `book_table`")
        connection.execSQL("DROP TABLE IF EXISTS `language_table`")
        connection.execSQL("DROP TABLE IF EXISTS `author_table`")
        connection.execSQL("DROP TABLE IF EXISTS `BookLanguageCrossRef`")
        connection.execSQL("DROP TABLE IF EXISTS `BookAuthorCrossRef`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsBookTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBookTable.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("description", TableInfo.Column("description", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("imageUrl", TableInfo.Column("imageUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("firstPublishYear", TableInfo.Column("firstPublishYear", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("ratingAverage", TableInfo.Column("ratingAverage", "REAL", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("ratingCount", TableInfo.Column("ratingCount", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("numPagesMedian", TableInfo.Column("numPagesMedian", "INTEGER", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBookTable.put("numEditions", TableInfo.Column("numEditions", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBookTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBookTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBookTable: TableInfo = TableInfo("book_table", _columnsBookTable,
            _foreignKeysBookTable, _indicesBookTable)
        val _existingBookTable: TableInfo = read(connection, "book_table")
        if (!_infoBookTable.equals(_existingBookTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |book_table(com.sample.bookpedia.feat_book.data.database.entities.BookEntity).
              | Expected:
              |""".trimMargin() + _infoBookTable + """
              |
              | Found:
              |""".trimMargin() + _existingBookTable)
        }
        val _columnsLanguageTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLanguageTable.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLanguageTable.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLanguageTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLanguageTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLanguageTable: TableInfo = TableInfo("language_table", _columnsLanguageTable,
            _foreignKeysLanguageTable, _indicesLanguageTable)
        val _existingLanguageTable: TableInfo = read(connection, "language_table")
        if (!_infoLanguageTable.equals(_existingLanguageTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |language_table(com.sample.bookpedia.feat_book.data.database.entities.LanguageEntity).
              | Expected:
              |""".trimMargin() + _infoLanguageTable + """
              |
              | Found:
              |""".trimMargin() + _existingLanguageTable)
        }
        val _columnsAuthorTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAuthorTable.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuthorTable.put("authorName", TableInfo.Column("authorName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAuthorTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAuthorTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAuthorTable: TableInfo = TableInfo("author_table", _columnsAuthorTable,
            _foreignKeysAuthorTable, _indicesAuthorTable)
        val _existingAuthorTable: TableInfo = read(connection, "author_table")
        if (!_infoAuthorTable.equals(_existingAuthorTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |author_table(com.sample.bookpedia.feat_book.data.database.entities.AuthorEntity).
              | Expected:
              |""".trimMargin() + _infoAuthorTable + """
              |
              | Found:
              |""".trimMargin() + _existingAuthorTable)
        }
        val _columnsBookLanguageCrossRef: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBookLanguageCrossRef.put("bookId", TableInfo.Column("bookId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookLanguageCrossRef.put("languageId", TableInfo.Column("languageId", "INTEGER",
            true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBookLanguageCrossRef: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBookLanguageCrossRef: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBookLanguageCrossRef: TableInfo = TableInfo("BookLanguageCrossRef",
            _columnsBookLanguageCrossRef, _foreignKeysBookLanguageCrossRef,
            _indicesBookLanguageCrossRef)
        val _existingBookLanguageCrossRef: TableInfo = read(connection, "BookLanguageCrossRef")
        if (!_infoBookLanguageCrossRef.equals(_existingBookLanguageCrossRef)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |BookLanguageCrossRef(com.sample.bookpedia.feat_book.data.database.entities.BookLanguageCrossRef).
              | Expected:
              |""".trimMargin() + _infoBookLanguageCrossRef + """
              |
              | Found:
              |""".trimMargin() + _existingBookLanguageCrossRef)
        }
        val _columnsBookAuthorCrossRef: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBookAuthorCrossRef.put("bookId", TableInfo.Column("bookId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookAuthorCrossRef.put("authorId", TableInfo.Column("authorId", "INTEGER", true, 2,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBookAuthorCrossRef: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBookAuthorCrossRef: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBookAuthorCrossRef: TableInfo = TableInfo("BookAuthorCrossRef",
            _columnsBookAuthorCrossRef, _foreignKeysBookAuthorCrossRef, _indicesBookAuthorCrossRef)
        val _existingBookAuthorCrossRef: TableInfo = read(connection, "BookAuthorCrossRef")
        if (!_infoBookAuthorCrossRef.equals(_existingBookAuthorCrossRef)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |BookAuthorCrossRef(com.sample.bookpedia.feat_book.data.database.entities.BookAuthorCrossRef).
              | Expected:
              |""".trimMargin() + _infoBookAuthorCrossRef + """
              |
              | Found:
              |""".trimMargin() + _existingBookAuthorCrossRef)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "book_table", "language_table",
        "author_table", "BookLanguageCrossRef", "BookAuthorCrossRef")
  }

  public override fun clearAllTables() {
    super.performClear(false, "book_table", "language_table", "author_table",
        "BookLanguageCrossRef", "BookAuthorCrossRef")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(FavouriteBookDao::class, FavouriteBookDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun bookDao(): FavouriteBookDao = _favouriteBookDao.value
}
