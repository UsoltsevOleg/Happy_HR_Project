# happy.hr
### Автор
- Олег Усольцев

### Технологии
- Spring Boot
- Hibernate
- PostgreSQL
- Flyway
- Swagger
- Junit
- Lombok
- Mapstruct
- Docker
- Gradle

### Общее описание приложения
Приложение позволяет собирать необходимую информацию
о проекте в вашей компании и о требованиях к соискателям
в виде карточек. Они предназначены для снижения трудоемкости
по сбору данных рекрутерами.

### Структура

Основной пакет: `com.example.happy.hr`
- Пакет `com.example.happy.hr.config` содержит классы-конфигурации (CORS, Swagger, PostgreSQL)
- Пакет `com.example.happy.hr.controllers` содержит классы-контроллеры
- Пакет `com.example.happy.hr.controllers.query.params` содержит классы-обертки для параметров HTTP-запросов
- Пакет `com.example.happy.hr.domain` содержит классы-сущности и классы-обертки для выборки подмножества столбцов таблиц в базк данных
- Пакет `com.example.happy.hr.exception.handler` содержит обработчики исключений
- Пакет `com.example.happy.hr.json` содержит DTO, а также мапперы DTO и классов-сущностей
- Пакет `com.example.happy.hr.repositories` содержит интерфейсы репозиториев
- Пакет `com.example.happy.hr.services` содержит интерфейсы сервисов
- Папка `resources/db.migration` содержит скрипты миграций

### API endpoints:

- `GET /` возвращает index.html
- `GET /api/users/auth` возвращает данные встроенного пользователя, т.к. пока нет авторизации
- `POST /api/cards/new` предназначено для создания новой карточки
- `PUT /api/cards/{id}` предназначено для редактирования сущесвтующей карточки
- `GET /api/cards/registry` возвращает страницу реестра проектов
- `GET /api/cards/{id}` возвращает данные выбранной карточки
- `DELETE /api/cards/{id}` удаляет выбранную карточку
- `PUT /api/cards/archive/{id}` архивирует карточку
- `DELETE DELETE /api/cards/archive/{id}` восстанавливает карточку из архива

### Описание классов и интерфейсов
- `com.example.happy.hr.config.WebConfig` содержит конфигурации для CORS и Swagger
- `com.example.happy.hr.config.SwaggerConfig` содержит конфигурацию Swagger для генерации html с описанием API
- `com.example.happy.hr.config.PostgreSQL94CustomDialect` - добавляет поддержку строковых массивов PostgreSQL
- `com.example.happy.hr.controllers.query.params.PageIngo` - класс-обертка с номером и размером страницы реестра проектов
- `com.example.happy.hr.controllers.query.params.ProjectRegistryFilter` - класс-обертка с фильтрами для поика проектов в реестре
- `com.example.happy.hr.controllers.DefaultController` - контроллер, возвращающий index.html при запросе на `GET /`
- `com.example.happy.hr.controllers.ProjectCardController` - контроллер для работы с краточками и реестром проектов
- `com.example.happy.hr.controllers.UserController` - контроллер, возвращающий dummy data о пользователе
- `com.example.happy.hr.domain.entities.Location` - класс-сущность (локация)
- `com.example.happy.hr.domain.entities.PossibleWorkSchedule` - класс-сущность (сдвиг по времени)
- `com.example.happy.hr.domain.entities.ProjectCard` - класс-сущность (карточка проекта)
- `com.example.happy.hr.domain.entities.ProjectModel` - класс-сущность (модель проекта)
- `com.example.happy.hr.domain.entities.ProjectType` - класс-сущность (тип проекта)
- `com.example.happy.hr.domain.entities.Team` - класс-сущность (команда)
- `com.example.happy.hr.domain.entities.User` - класс-сущность (пользователь)
- `com.example.happy.hr.domain.entities.WotkingHoursPattern` - класс-сущность (график работы)
- `com.example.happy.hr.domain.wrappers.ProjectCardWrapper` - класс-обертка для формирования записи реестра проекта
- `com.example.happy.hr.exception.handler.CustomExceptionHandler` - обработчик исключений
- `com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo` - DTO для передачи записи из реестра
- `com.example.happy.hr.json.dto.auxiliary` - класс-обертка с данными для сортировки реестра
- `com.example.happy.hr.json.dto.LocationDto` - DTO для Location
- `com.example.happy.hr.json.dto.PossibleWorkScheduleDto` - DTO для PossibleWorkSchedule
- `com.example.happy.hr.json.dto.ProjectCardDto` - DTO для ProjectCard
- `com.example.happy.hr.json.dto.ProjectModelDto` - DTO для ProjectModel
- `com.example.happy.hr.json.dto.ProjectTypeDto` - DTO для ProjectType
- `com.example.happy.hr.json.dto.TeamDto` - DTO для Team
- `com.example.happy.hr.json.dto.UserDto` - DTO для User
- `com.example.happy.hr.json.dto.WorkingHoursPatternDto` - DTO для WorkingHoursPattern
- `com.example.happy.hr.json.mapper.LocationMapper` - маппер для LocationDto и Location
- `com.example.happy.hr.json.mapper.PossibleWorkScheduleMapper` - маппер для PossibleWorkScheduleDto и PossibleWorkSchedule
- `com.example.happy.hr.json.mapper.ProjectCardMapper` - маппер для ProjectCardDto и ProjectCard
- `com.example.happy.hr.json.mapper.ProjectModelMapper` - маппер для ProjectModelDto и ProjectModel
- `com.example.happy.hr.json.mapper.ProjectTypeMapper` - маппер для ProjectTypeDto и ProjectType
- `com.example.happy.hr.json.mapper.Team` - маппер для TeamDto и Team
- `com.example.happy.hr.json.mapper.UserMapper` - маппер для UserDto и User
- `com.example.happy.hr.json.mapper.WorkingHoursPatternMapper` - маппер для WorkingHoursPatternDto и WorkingHoursPattern
- `com.example.happy.hr.repositories.custom.CustomProjectCardRepository` - репозиторий для динамического формирования запросов в реестр
- `com.example.happy.hr.repositories.custom.impl.CustomProjectCardRepositoryImpl` - реализация CustomProjectCardRepository
- `com.example.happy.hr.repositories.LocationRepository` - репозиторий для Location
- `com.example.happy.hr.repositories.PossibleWorkScheduleRepository` - репозиторий для PossibleWorkSchedule
- `com.example.happy.hr.repositories.ProjectCardRepository` - репозиторий для ProjectCard
- `com.example.happy.hr.repositories.ProjectModelRepository` - репозиторий для ProjectModel
- `com.example.happy.hr.repositories.ProjectTypeRepository` - репозиторий для ProjectType
- `com.example.happy.hr.repositories.TeamRepository` - репозиторий для TeamRepository
- `com.example.happy.hr.repositories.UserRepository` - репозиторий для UserRepository
- `com.example.happy.hr.repositories.WorkingHoursPatternRepository` - репозиторий для WorkingHoursPattern
- `com.example.happy.hr.services.ProjectCardService` - сервис для ProjectCard
- `com.example.happy.hr.services.UserService` - сервис для User
- `com.example.happy.hr.services.impl.ProjectCardServiceImpl` - реализация ProjectCardService
- `com.example.happy.hr.services.impl.UserServiceImpl` - реализация UserService

### Сборка и запуск

- Перед запуском проекта убедитесь,
что у вас уставнвлены либо [Java](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html),
либо [Docker](https://www.docker.com/)
- Скачайте архив проекта и распакуйте его

### Java
- Если на вашей машине усатновлена только java,
перейдите в терминале в корневую папку проекта и выполните `./gradlew clean build`,
либо `gradlew.bat clean build` (выбор команды зависит от вашей ОС)
- Затем выполните команду `java -jar build/libs/happy.hr-0.0.1-SNAPSHOT.jar`
- Сервер будет доступен по адресу `http://localhost:8080`

### Docker
- Если на вашей машине есть только Docker, 
перейдите в терминале в корневую папку проекта и выполните `docker-compose up`
- Сервер будет доступен по адресу `http://localhost:8080`

***Для более подробного описания API перейдите по `http://localhost:8080/swagger-ui.html`***
