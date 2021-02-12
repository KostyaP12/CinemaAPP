package com.example.cinemaapp.model

import android.os.Parcelable
import com.example.cinemaapp.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OriginalSourcePreview(
    val cardViewFilms: CardViewFilms = getDefaultPreview()
):Parcelable

fun getDefaultPreview() = CardViewFilms(title = "Title", description = "Films`s description", poster = R.drawable.heroes)  // когда пройдем Picasso никаких ссылок на картинки не будет
fun getAllFilms(): List<OriginalSourcePreview>{
    return listOf(
        OriginalSourcePreview(CardViewFilms("Москва слезам не верит", "Москва пятидесятых годов.", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Мстители финал", "Эпохальное завершение супергеройской франшизы, самый кассовый фильм в истории кино.", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Я Робот", "Искусственный интеллект и армия роботов лишают людей свободы ради их же безопасности. Уилл Смит против", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Звездные войны атака клонов", "Роман Энакина и Падме в преддверии битвы андроидов, клонов и джедаев. Самая романтичная часть космической саги", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Послезавтра", "Грозное пророчество: изменения климата, замерзшая планета, отважные подростки. Красота, леденящая кровь.", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Пираты карибского моря на странных берегах", "Джек Воробей против Черной бороды", R.drawable.heroes))
    )
}
fun getFavoritesFilms(): List<OriginalSourcePreview>{
    return listOf(
        OriginalSourcePreview(CardViewFilms("Зеленая книга", "Путешествие итальянца-вышибалы и чернокожего пианиста — комедия-лауреат премии «Оскар» за лучший фильм", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Ford против Ferrari", "Биография знаменитого гонщика Кена Майлза и драма об искренней и чистой любви к быстрой езде", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Джентельмены", "Успешное возвращение Гая Ричи к корням — острая и живая криминальная комедия с блестящим актерским составом", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Волк с Уолл-Стрит", "Финансовая трагикомедия одного эксцентричного банкира", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Реквием по мечте", "Жизнь героев катится под откос из-за наркозависимости. Безжалостная драма Даррена Аронофски", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("1+1", "Бывший зек возвращает вкус к жизни чопорному аристократу, прикованному к инвалидному креслу", R.drawable.heroes))
        )
}
fun getTopRatingFilms(): List<OriginalSourcePreview>{
    return listOf(
        OriginalSourcePreview(CardViewFilms("Побег из Шоушенка", "Выдающаяся драма о силе таланта, важности дружбы, стремлении к свободе и Рите Хэйворт", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Зеленая миля", "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Властелин колец возвращение короля", "Арагорн штурмует Мордор, а Фродо устал бороться с чарами кольца. Эффектный финал саги, собравший 11 «Оскаров»", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Интерстеллар", "Фантастический эпос про задыхающуюся Землю, космические полеты и парадоксы времени. «Оскар» за спецэффекты", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Властелин колец: Братство кольца", "Фродо Бэггинс отправляется спасать Средиземье. Первая часть культовой фэнтези-трилогии Питера Джексона", R.drawable.heroes)),
        OriginalSourcePreview(CardViewFilms("Властелин колец: Две крепости", "Голлум ведет хоббитов в Мордор, а великие армии готовятся к битве. Два «Оскара»", R.drawable.heroes))
    )
}


