# TodoList

## Описание
Приложение для составления списков задач с возможностью последующего отслеживания прогресса. Пользователи могут добавлять задачи, указывать названия, описания, выбирать даты выполнения и даже прикреплять изображения. Данные хранятся локально с использованием Room.

## Скриншоты
<img src="https://github.com/user-attachments/assets/849ced61-eb9f-4255-ba52-61ac3c4a3d18" alt="Screenshot 1" width="150"/>
<img src="https://github.com/user-attachments/assets/41bd3f0e-ec34-4e4c-83f3-952779cb4cac" alt="Screenshot 1" width="150"/>
<img src="https://github.com/user-attachments/assets/2c57f4ec-097f-468e-a310-67e56985efb8" alt="Screenshot 1" width="150"/>
<img src="https://github.com/user-attachments/assets/1b6b612c-518b-446c-a7cc-8cdc07a724e8" alt="Screenshot 1" width="150"/>

## Технологии и Библиотеки
- Язык программирования: **Kotlin**
- Асинхронные операции: **Coroutines**, **Flow**
- База данных: **Room**
- UI Framework: **Jetpack Compose**
- Навигация: **Jetpack Navigation**
- Изображения: **Coil**
- Аннотации: **KSP**
- Дизайн: **Material 3**

## Структура Проекта
Проект организован согласно принципу чистой архитектуры с применением паттерна MVI. Основные пакеты распределены следующим образом:
- **data**: слой взаимодействия с локальной базой данных
- **domain**: бизнес логика
- **presentation**: взаимодействие с UI
- **ui**: компоненты интерфейса пользователя

## Установка и Запуск
### Вариант 1: Загрузка APK-файла
Загрузите последнюю версию APK файла из релиза:
- Скачать: [APK](https://github.com/marcosuper97/todolist/releases/tag/v.1.0.0)

### Вариант 2: Сборка из исходников
1. Скопируйте репозиторий:
   ```bash
   git clone https://github.com/marcosuper97/todolist.git
   ```
2. Откройте проект в Android Studio и соберите приложение.

## Ограничения
Минимальная поддерживаемая версия Android: **API Level 24 (Android 7.0, Nougat)**.

## Обратная Связь
Связаться с разработчиком можно через Telegram: [@grp997](tg://resolve?domain=grp997).
