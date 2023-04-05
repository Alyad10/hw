package com.alya.kotlin_6_month.data.base
import com.alya.kotlin_6_month.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {
    protected fun <T> doRequest( request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(request))

        }catch (ioExeption : IOException){
            emit(Resource.Error(ioExeption.localizedMessage ?:"Unknown error!"))
        }

    }.flowOn(Dispatchers.IO)

    }
