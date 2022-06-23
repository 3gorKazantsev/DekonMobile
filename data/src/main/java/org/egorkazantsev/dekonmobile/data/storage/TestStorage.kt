package org.egorkazantsev.dekonmobile.data.storage

import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.model.Owner
import java.util.*
import kotlin.math.roundToInt

val models = listOf(
    Model(
        UUID.fromString("e015cd30-f085-49d8-b186-3475d86f1e2d"), "Модель 2 крит",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "Главная матрица", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    ),
    Model(
        UUID.fromString("765ad7c9-05aa-4a71-a76a-003298ad4b81"), "Model 1 mtr 3 crt",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "mtr 1", 2.1,
                leftElement = Criteria(
                    UUID.randomUUID(), "crt 2", 1.1,
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "crt 3", 1.4
                )
            )
        )
    ),
    Model(
        UUID.randomUUID(), "Качество работы управляющей компании",
        Owner(UUID.randomUUID(), "Зубенко Михаил Петрович"),
        Matrix(
            UUID.randomUUID(), "Качество управления МКД", random(),
            leftElement = Matrix(
                UUID.randomUUID(), "Критерий оценки качества предоставляемой информации", random(),
                leftElement = Matrix(
                    UUID.randomUUID(), "Предоставление и доступность информации", random(),
                    leftElement = Criteria(
                        UUID.randomUUID(),
                        "Предоставление информации о перечне услуг, их стоимости",
                        random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Доступность информации", random()
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Взаимодействие с потребителями", random(),
                    leftElement = Criteria(
                        UUID.randomUUID(),
                        "Предоставление годового плана работ и полного отчета об исполнении плана",
                        random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(),
                        "Взаимодействие с собственниками помещений для получения обратной связи",
                        random()
                    )
                )
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "Критерий оценки качества услуг и рабор упр. орг.", random(),
                leftElement = Matrix(
                    UUID.randomUUID(), "Обслуживание МКД", random(),
                    leftElement = Criteria(
                        UUID.randomUUID(), "Содержание и ремон общего имущества МКД", random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Предоставление коммунальных услуг", random()
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Работа ресурсоснабжающими организациями", random(),
                    leftElement = Criteria(
                        UUID.randomUUID(),
                        "Подписание договоров с РСО, контроль качества услуг, сбор средств и регулярная оплата полученных услуг",
                        random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(),
                        "Разработка и прим. мероприятий по энергоснабжению и повышению энергической эффективности МКД",
                        random()
                    )
                )
            )
        )
    ),
    Model(
        UUID.randomUUID(), "Качество торгово-развлекательного комплекса",
        Owner(UUID.randomUUID(), "Михаил Палыч Терентьев"),
        Matrix(
            UUID.randomUUID(), "Качество упраления МКД", 2.4,
            leftElement = Matrix(
                UUID.randomUUID(), "Критерии оценки качества предоставляемой информации", 1.9,
                leftElement = Matrix(
                    UUID.randomUUID(), "Предоставление и доступность информации", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(),
                        "Предоставление информации о перечне услуг, их стоимости",
                        1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Доступность информации", 1.9
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Взаимодействие с потребителем", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(),
                        "Предоставление годового плана работ и полного отчета об исполнении плана",
                        1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(),
                        "Взаимодействие с собственниками помещений для получения обратной связи",
                        1.9
                    )
                )
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "Критерии оценки качества услуг и работ упр. орг.", 2.1,
                leftElement = Matrix(
                    UUID.randomUUID(), "Обслуживание МКД", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Содержание и ремонт общего имущества МКД", 1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Предоставление коммунальных услуг", 1.9
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Работа с ресурсоснабжающими организациями ", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Подписание договоров с РСО, контроль качества услуг, сбор средств и регулярная оплата полученных услуг", 1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Разработка и прим. мероприятий по энергосбержению и повышению энергетической эффективности МКД", 1.9
                    )
                )
            )
        )
    ), Model(
        UUID.randomUUID(), "Привлекательность арендатора для объекта",
        Owner(UUID.randomUUID(), "Эрих Мария Ремарк"),
        Matrix(
            UUID.randomUUID(), "Привлекательность арендатора для объекта", 2.4,
            leftElement = Matrix(
                UUID.randomUUID(), "Привлекательность товара", 1.9,
                leftElement = Matrix(
                    UUID.randomUUID(), "Концепция", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Вид товара", 1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Востребованность товара", 1.9
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Соотношение цены и качества товара", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Цена товара", 1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Качество товара", 1.9
                    )
                )
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "Требования к арендодателю", 2.1,
                leftElement = Matrix(
                    UUID.randomUUID(), "Требования к помещения", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Площадь", 1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Планировка", 1.9
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Требования к договору", 1.9,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Арендная плата", 1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Продолжительность контракта", 1.9
                    )
                )
            )
        )
    ), Model(
        UUID.randomUUID(),
        "Качество предоставления услуг по содержанию здания МКД и его придомовой площади",
        Owner(UUID.randomUUID(), "Михаил Афанасьевич Булгаков"),
        Matrix(
            UUID.randomUUID(), "Качество предоставления услуг по содержанию здания МКД и его придомовой территории", 2.5,
            leftElement = Matrix(
                UUID.randomUUID(), "Факторы, связанные с деятельностью управляющей компании", 2.5,
                rightElement = Matrix(
                    UUID.randomUUID(), "Качество обслуживания здания МКД", 2.5,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Текущий и капитальный ремонт", 3.1,
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Санитарное содержание здания МКД", 2.5
                    )
                ),
                leftElement = Matrix(
                    UUID.randomUUID(), "Качество обслуживания придомовой территории", 3.0,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Санитарное содержание придомовой территории", 3.1
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Озленение придомовой территории", 3.0
                    )
                )
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "Факторы, связанные с удовлетворенностью собственников", 2.8,
                leftElement = Criteria(
                    UUID.randomUUID(), "Качество обслуживания здания МКД", 2.8
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "Качество обслуживания придомовой территории", 3.0
                )
            )
        )
    ), Model(
        UUID.randomUUID(), "Наиболее эффективный вариант соотношения площадей ТРЦ",
        Owner(UUID.randomUUID(), "Федор Михайлович Достоевский"),
        Matrix(
            UUID.randomUUID(), "Интегральная эффективность", 3.0,
            leftElement = Matrix(
                UUID.randomUUID(), "Физическая осуществимость", 3.0,
                leftElement = Matrix(
                    UUID.randomUUID(), "Маркетинговая эффективность", 4.0,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Востребованность составляющих", 4.0
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Привлекательность составляющих", 4.0
                    )
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "Возможность реализации", 2.0
                )
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "Экономическая привлекательность", 3.0,
                leftElement = Criteria(
                    UUID.randomUUID(), "Возможные риски", 2.0
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "Доходность", 3.0
                )
            )
        )
    ), Model(
        UUID.randomUUID(), "Оценка внутренней отделки",
        Owner(UUID.randomUUID(), "Антон Павлович Чехов"),
        Matrix(
            UUID.randomUUID(), "Уровень внутренней отделки", 2.2,
            leftElement = Matrix(
                UUID.randomUUID(), "Техническое оснащение дома", 2.2,
                leftElement = Matrix(
                    UUID.randomUUID(), "Климат контроль дома", 2.2,
                    leftElement = Criteria(
                        UUID.randomUUID(), "Тепловой режим", 1.2
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Вентиляция", 3.0
                    )
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "Электрооборудование, бытовая техника", 2.0
                )
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "Внешний вид внутренней отдели дома", 2.2,
                leftElement = Criteria(
                    UUID.randomUUID(), "Материалы, использованные в отделке", 2.2
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "План оформления помещений (дизайн-проект/определенный концепт)", 2.0
                )
            )
        )
    )
)

fun random() = ((10..38).random() / 10.0)