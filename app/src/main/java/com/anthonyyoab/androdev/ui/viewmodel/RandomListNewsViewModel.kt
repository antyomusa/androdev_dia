package com.anthonyyoab.androdev.ui.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anthonyyoab.androdev.data.remote.NewsRemoteDataSource
import com.anthonyyoab.androdev.data.repository.NewsRepository
import com.anthonyyoab.androdev.data.service.NewsService
import com.anthonyyoab.androdev.retrofit.NewsRetrofit
import com.anthonyyoab.androdev.ui.model.Article
import com.anthonyyoab.androdev.utils.ViewState
import com.anthonyyoab.androdev.ui.model.BeritaResponse
import com.anthonyyoab.androdev.ui.model.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RandomListNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private  val compositeDisposable = CompositeDisposable()
    private val verifyApiResponseLiveData = MutableLiveData<BeritaResponse>()
    private val randomNewsLiveData = MutableLiveData<List<Article>>()
    private val insertNewsLiveData = MutableLiveData<ViewState<Boolean>>()

    fun verifyApi(key: String){
        compositeDisposable.add(
            newsRepository.verifyApi(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<BeritaResponse>() {
                    override fun onSuccess(t: BeritaResponse) {
                        verifyApiResponseLiveData.value = t
                        randomNewsLiveData.value = t.articles
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    fun insertNews(article: Article){
        compositeDisposable.add(
            newsRepository.insertNews(article)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Long>(){
                    override fun onSuccess(t: Long) {
                        insertNewsLiveData.value = ViewState.success(true)
                    }

                    override fun onError(e: Throwable) {
                        insertNewsLiveData.value = ViewState.error("failed to insert data", null )
                    }

                })
        )
    }

    fun getVerifyApiResponseLiveData(): MutableLiveData<List<Article>> = randomNewsLiveData

    fun getInsertNewsLiveData(): MutableLiveData<ViewState<Boolean>>  = insertNewsLiveData

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

}