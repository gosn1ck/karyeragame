import React from 'react';
import {Formik, Field, Form, ErrorMessage} from 'formik';
import {Link} from "react-router-dom";


import {validationRegisterSchema, handleSubmit} from '../../../js/components/validation';

const RegisterForm = () => {
    return (
        <Formik
            initialValues={{
                login: '',
                email: '',
                password: '',
                passwordConf: '',
            }}
            validationSchema={validationRegisterSchema}
            onSubmit={handleSubmit}
        >
            <Form method="post" id="register" className="form form__register">

            {/* <label htmlFor="file" className="form-box__label-img"></label>
        <input type="file" id="file"/> */}

            {/* <div className="form-group"> */}
            <label className="form-box__label">Логин</label>
            <ErrorMessage name="login" component="div" className="error-message"/>
            <Field type="text" name="login" className="form-box__input"/>
            {/* </div> */}

            {/* <div className="form-group"> */}
            <label className="form-box__label">E-mail</label>
            <ErrorMessage name="email" component="div" className="error-message"/>
            <Field type="text" name="email" className="form-box__input"/>
            {/* </div> */}

            {/* <div className="form-group"> */}
            <label className="form-box__label">Пароль</label>
            <ErrorMessage name="password" component="div" className="error-message"/>
            <Field type="password" name="password" className="form-box__input"/>
            {/* </div> */}

            {/* <div className="form-group"> */}
            <label className="form-box__label">Подтверждение пароля</label>
            <ErrorMessage name="passwordConf" component="div" className="error-message"/>
            <Field type="password" name="passwordConf" className="form-box__input"/>
            {/* </div> */}
            <>
                <button className="btn-form" type="submit">
                    Зарегистрироваться
                    {/* <Link to='/profile'>Зарегистрироваться</Link> */}
                    
                </button>
            </>

            </Form>
        </Formik>
    );
};

export default RegisterForm;