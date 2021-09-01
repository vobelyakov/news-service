package com.v72.novatek.newsservice;

import com.v72.novatek.newsservice.core.interfaces.IAuthorService;
import com.v72.novatek.newsservice.core.models.Author;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;
import com.v72.novatek.newsservice.infrastructure.repository.AuthorRepository;
import com.v72.novatek.newsservice.infrastructure.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedDatabase {
    private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

    @Autowired
    private IAuthorService authorService;

    @Bean
    String initDatabase(NewsRepository repository, AuthorRepository authRepository) {
        authRepository.save(new Author("Pushkin"));
        authRepository.save(new Author("Lermontov"));
        authRepository.save(new Author("Chehov"));

        repository.save(new News("title"
                ,"content"
                , authorService.getRandomAuthor(), NewsCategory.Sport));

        repository.save(new News("Рубль вечером усилил темпы роста к доллару и евро благодаря аппетиту к риску"
                ,"Доллар и евро ускорили снижение на Московской бирже в среду вечером, рубль растет к бивалютной корзине благодаря увеличению аппетита " +
                "к риску со стороны глобальных инвесторов и вслед за другими валютами emerging markets. Курс доллара США составил 72,92 руб./$1 в 19:00 " +
                "по московскому времени, что на 30,25 копейки ниже уровня закрытия предыдущих торгов. При этом доллар торгуется ниже 73 руб./$1 впервые с 6 " +
                "августа. Евро стоил 86,41 руб./EUR1, подешевев на 3,75 копейки. Стоимость бивалютной корзины ($0,55 и EUR0,45) уменьшилась на 18,3 копейки - " +
                "до 78,99 рубля."
                , authorService.getRandomAuthor(), NewsCategory.Economy));

        repository.save(new News("Пенсии в РФ в реальном выражении снижаются шестой месяц подряд"
                ,"Высокая инфляция в РФ в 2021 году привела к снижению пенсий в РФ в реальном выражении (с поправкой на инфляцию): в феврале 2021 года - " +
                "на 0,1% в годовом сравнении, в марте - на 0,2%, в апреле - на 0,1%, в мае - на 0,5%, в июне на 1,0%, в июле - на 0,9%, следует из опубликованных " +
                "в среду данных Росстата."
                , authorService.getRandomAuthor(), NewsCategory.Economy));

        repository.save(new News("Цены на нефть перешли к снижению на новостях из России"
                ,"Вице-премьер РФ Александр Новак заявил журналистам, что российские нефтяные компании готовы наращивать добычу нефти сверх объемов и " +
                "темпов, установленных сделкой ОПЕК+. \"Компании спокойно восстанавливают добычу в соответствии с теми требованиями, которые до них доводят. " +
                "И даже говорят, что могут быстрее и больше\", - сказал он."
                , authorService.getRandomAuthor(), NewsCategory.Economy));

        repository.save(new News("Памфилова пригрозила Петербургу прокуратурой за рокировки в местных избиркомах"
                ,"Массовый переход председателей участковых избиркомов из одного района Петербурга в другой за три недели до выборов встревожил " +
                "Центральную избирательную комиссию, сообщила председатель ЦИК Элла Памфилова на заседании в среду."
                , authorService.getRandomAuthor(), NewsCategory.Politics));

        repository.save(new News("Лукашенко сообщил о планах утвердить 28 союзных программ с Россией"
                ,"резиденты Белоруссии и России планируют в октябре-ноябре 2021 года подписать пакет из 28 \"дорожных карт\" по интеграции, заявил " +
                "белорусский лидер Александр Лукашенко. По его словам, это произойдет на заседании Высшего государственного совета союзного государства, " +
                "передает \"БелТА\"."
                , authorService.getRandomAuthor(), NewsCategory.Politics));

        repository.save(new News("Семь золотых медалей заработали россияне на Паралимпиаде в среду"
                ,"Обладателями наград высшего достоинства в среду стали легкоатлеты Андрей Вдовин в беге на 400 м, Галина Липатникова в толкании ядра, " +
                "Муса Таймазов в метании клаба, пловцы Мария Павлова на дистанции 100 м брассом, Андрей Калина на 200 м комплексным плаванием, " +
                "Виктория Ищиулова на 50 вольным стилем, велогонщик Руслан Кузнецов в шоссейных соревнованиях."
                , authorService.getRandomAuthor(), NewsCategory.Sport));

        repository.save(new News("Россиянин Таймазов выиграл Паралимпиаду в метании клаба с рекордом мира"
                ,"Российский легкоатлет Муса Таймазов стал чемпионом летних Паралимпийских игр в Токио, выиграв соревнования в метании клаба." +
                "Его результат – 35,42 м, что является новым мировым рекордом." +
                "Обладатель предыдущего - 34,71 - рекорда мира серб Желько Димитриевич занял второе место, улучшив то свое достижение (35,29)."
                , authorService.getRandomAuthor(), NewsCategory.Sport));

        return "News created";
    }
}
