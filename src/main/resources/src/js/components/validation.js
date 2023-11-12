//

import axios from "axios";
import * as Yup from 'yup';
// import { Formik } from 'formik';

const validationSchema = Yup.object().shape({
  login: Yup.string().required('Обязательное поле'),
  email: Yup.string().email('Некорректный email').required('Обязательное поле'),
  password: Yup.string().min(6, 'Пароль должен содержать минимум 6 символов').required('Обязательное поле'),
  passwordConf: Yup.string()
    .oneOf([Yup.ref('password'), null], 'Пароли должны совпадать')
    .required('Обязательное поле'),
});

const handleSubmit = async (values) => {
  const NewUserDto = {
    username: values.login,
    email: values.email,
    password: values.password,
  };

    axios.post('http://localhost:8080/users/auth/register', NewUserDto, {
      headers: {
        'Content-Type': 'application/json',
      }
    })

    .then(response => {
      console.log("Успешно",response);
    })
    .catch(error => {
      console.error(error);
    });

};

export { validationSchema, handleSubmit };
