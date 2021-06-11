package com.ilham.moviesandtvshow

import androidx.paging.PagedList
import org.mockito.Mockito.*

object PagedListUtil {
    fun <T> mockPagedList (list: List<T>):PagedList<T>{
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[anyInt()]).then{ invoke->
            val index = invoke.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}