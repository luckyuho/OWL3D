package com.hi.dhl.wl3d.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.wl3d.data.entity.DataEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(dataList: List<DataEntity>)

    @Query("SELECT * FROM DataEntity")
    fun getData(): PagingSource<Int, DataEntity>

    @Query("DELETE FROM DataEntity")
    suspend fun clearData()

}