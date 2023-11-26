import React, { createContext } from 'react';
import ReactDOM from 'react-dom/client';

import App from './components/app/app';

import Store from './store/store';

import reportWebVitals from './reportWebVitals';

import './style/style.css';
import './js/script.js';


// Создание экземпляра хранилища
const store = new Store();

// Создание контекста
export const Context = createContext({
  store,
});

// Обертка приложения в StrictMode
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

    <Context.Provider value={{ store }}>
      <App />
    </Context.Provider>
  </React.StrictMode>
);

// Отчет о веб-показателях
reportWebVitals();


// import React from 'react';
// import ReactDOM from 'react-dom/client';
// import './style/style.css';
// import './js/script.js';

// import App from './components/app/app';
// import reportWebVitals from './reportWebVitals';

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// reportWebVitals();

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals