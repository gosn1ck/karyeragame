//

import * as Yup from 'yup';
// import { Formik } from 'formik';

const validationSchema = Yup.object().shape({
  login: Yup.string().max(20, "Имя пользователя не может быть длинее 20 символов").required('Обязательное поле'),
  email: Yup.string().email('Некорректный email').required('Обязательное поле'),
  password: Yup.string().min(6, 'Пароль должен содержать минимум 6 символов').required('Обязательное поле'),
  passwordConf: Yup.string()
    .oneOf([Yup.ref('password'), null], 'Пароли должны совпадать')
    .required('Обязательное поле'),
});

const handleSubmit = (values, { setSubmitting }) => {
  // Здесь можно добавить логику отправки данных на сервер
  fetch('./', {
    method: 'POST',
    body: JSON.stringify(values),
    headers: {
      'Content-Type': 'application/json',
    },
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data); // Обработка ответа от сервера
    })
    .catch((error) => {
      console.error('Ошибка:', error);
    })
    .finally(() => {
      setSubmitting(false);
    });
};

export { validationSchema, handleSubmit };