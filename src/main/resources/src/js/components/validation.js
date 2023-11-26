import axios from "axios";
import * as Yup from 'yup';

const validationRegisterSchema = Yup.object().shape({
  login: Yup
    .string()
    // .matches(
    //   /^[^\s][a-zA-Z0-9][\sa-zA-Z0-9][\p{L}\p{N}\p{P}\p{Z}]\p{Graph}+$/,
    //   'Логин может состоять из кириллицы/латиницы, цифр от 0 до 9 и видимые символы'
    // )
    .required('Обязательное поле')
    .min(1, 'Минимальная длина 1 символ')
    .max(20, 'Максимальная длина 20 символов'),

  email: Yup
    .string()
    .matches(
      /^(?=.{1,255}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$/,
      'Некорректный формат email'
    )
    .required('Обязательное поле')
    .min(6, 'Минимальная длина 6 символов')
    .max(255, 'Максимальная длина 255 символов'),

  password: Yup
    .string()
    // .matches(
    //   /^[^\s][\p{ASCII}\p{Punct}\p{Graph}]+$/,
    //   'Пароль может состоять из латиницы, 0-9, видимые символы'
    // )
    .required('Обязательное поле')
    .min(5, 'Минимальная длина 5 символов')
    .max(100, 'Максимальная длина 100 символов'),
});



const handleSubmit = async (value) => {
  const NewUserDto = {
    // name: "Asemi1",
    // email: "valid@email.com",
    // password: "Valid_Password123",
    name: value.login,
    email: value.email,
    password: value.password,
  };

    axios.post('http://localhost:8080/api/v1/users/auth/sign-up', NewUserDto, {
      headers: {
        'Content-Type': 'application/json',
      }
    })

    .then(response => {
      console.log("Успешно",response);
    })
    .catch(error => {
      // console.error(error);
      console.error(error.response.data,error.response.status );
      // var errorResponse = error.response.status;
      // if(errorResponse = 409){
      //   console.log("lol");
        
      // }

    });

};


export { validationRegisterSchema, handleSubmit };
