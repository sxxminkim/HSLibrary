//import React, { createElement } from 'react';
import {createRoot} from 'react-dom/client';
import './index.css';
import HansungLibrary from './App';
import {BrowserRouter} from 'react-router-dom';

const element=document.getElementById('root');
const root=createRoot(element);
root.render(
    <BrowserRouter basename={process.env.PUBLIC_URL}>
        <HansungLibrary />
    </BrowserRouter>,
);

