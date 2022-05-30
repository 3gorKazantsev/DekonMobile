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
        Owner(UUID.randomUUID(), "Зубенко Михаил Петрович"),
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
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "Качество управления МКД", random(),
            leftElement = Matrix(
                UUID.randomUUID(), "Критерий оценки качества предоставляемой информации", random(),
                leftElement = Matrix(
                    UUID.randomUUID(), "Предоставление и доступность информации", random(),
                    leftElement = Criteria(
                        UUID.randomUUID(), "Предоставление информации о перечне услуг, их стоимости", random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Доступность информации", random()
                    )
                ),
                rightElement = Matrix(
                    UUID.randomUUID(), "Взаимодействие с потребителями", random(),
                    leftElement = Criteria(
                        UUID.randomUUID(), "Предоставление годового плана работ и полного отчета об исполнении плана", random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Взаимодействие с собственниками помещений для получения обратной связи", random()
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
                        UUID.randomUUID(), "Подписание договоров с РСО, контроль качества услуг, сбор средств и регулярная оплата полученных услуг", random()
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "Разработка и прим. мероприятий по энергоснабжению и повышению энергической эффективности МКД", random()
                    )
                )
            )
        )
    ),
    Model(
        UUID.randomUUID(), "Качество торгово-развлекательного комплекса",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    ),Model(
        UUID.randomUUID(), "Привлекательность арендатора для объекта",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    ),Model(
        UUID.randomUUID(), "Качество предоставления услуг по содержанию здания МКД и его придомовой площади",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    ),Model(
        UUID.randomUUID(), "Надежность застройщика",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    ),Model(
        UUID.randomUUID(), "Оценка компании на банкротство",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    )
)

fun random() = ((10..38).random() / 10.0)