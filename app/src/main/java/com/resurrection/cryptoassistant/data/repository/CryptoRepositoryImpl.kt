package com.resurrection.cryptoassistant.data.repository

import com.resurrection.cryptoassistant.data.db.dao.CryptoDao
import com.resurrection.cryptoassistant.data.model.CryptoChartModel
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.remote.CryptoApiService
import com.resurrection.cryptoassistant.util.Resource
import com.resurrection.cryptoassistant.util.getResourceByDatabaseRequest
import com.resurrection.cryptoassistant.util.getResourceByNetworkRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(private val cryptoApiService: CryptoApiService,private val cryptoDao: CryptoDao):TestRepository{
    override suspend fun getAllCrypto(): Flow<Resource<List<CryptoMarketModel>>> = flow {
        emit(getResourceByNetworkRequest { cryptoApiService.getAllCrypto() })
    }

    override suspend fun getCryptoDetailById(id: String): Flow<Resource<CryptoDetailItem>>  = flow{
        emit(getResourceByNetworkRequest { cryptoApiService.getCryptoById(id) })
    }

    override suspend fun getCryptoChartByID(id: String): Flow<Resource<CryptoChartModel>> = flow{
        emit(getResourceByNetworkRequest { cryptoApiService.getCryptoChartByID(id) })
    }

    override suspend fun insertFavoriteCrypto(cmm: CryptoMarketModel): Flow<Resource<Unit>> = flow {
        emit(getResourceByDatabaseRequest { cryptoDao.insertFavoriteCrypto(cmm) })
    }

    override suspend fun getCryptoFavorite(): Flow<Resource<List<CryptoMarketModel>>> = flow {
        emit(getResourceByDatabaseRequest { cryptoDao.getCryptoFavorite() })
    }

    override suspend fun getCryptoById(cryptoId: String): Flow<Resource<CryptoMarketModel>> = flow{
        emit(getResourceByDatabaseRequest { cryptoDao.getCryptoById(cryptoId) })
    }


}

/*class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService, private val movieDao: MovieDao,
) : MovieRepository {
    override suspend fun getMovieById(id: String, page: Int): Flow<Resource<SearchResults>> = flow {
        emit(getResourceByNetworkRequest { movieApiService.getMovieById(id, page) })
    }

    override suspend fun getMovieDetail(imdbId: String): Flow<Resource<MovieDetails>> = flow {
        emit(getResourceByNetworkRequest { movieApiService.getMovieDetail(imdbId) })
    }

    override suspend fun getMovieById(imdbID: String): Flow<Resource<SearchItem>> = flow {
        emit(getResourceByDatabaseRequest { movieDao.getMovieById(imdbID) })
    }

    override suspend fun getMovieByTitle(title: String): Flow<Resource<List<SearchItem>>> = flow {
        emit(getResourceByDatabaseRequest { movieDao.getMovieByTitle(title) })
    }

    override suspend fun insertMovie(movie: SearchItem): Flow<Resource<Unit>> = flow {
        emit(getResourceByDatabaseRequest { movieDao.insertMovie(movie) })
    }

    override suspend fun removeMovie(movie: SearchItem): Flow<Resource<Unit>> = flow {
        emit(getResourceByDatabaseRequest { movieDao.removeMovie(movie) })
    }

    override suspend fun getFavoriteMovies(): Flow<Resource<List<SearchItem>>> = flow {
        emit(getResourceByDatabaseRequest { movieDao.getFavoriteMovies() })
    }

}
*/