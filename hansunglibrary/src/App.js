//router
import {Button} from '@mui/material';
import {Link, BrowserRouter, Switch, Route} from 'react-router-dom';
import './App.css';
//import ImgCard from 'src/Card.js';
import Home from './pages/Home.js';

import Search from './pages/Search.js';
import Propose from './pages/Propose.js';
import Notice from './pages/Notice.js';
import Use from './pages/Use.js';

function HansungLibrary() {
  return (
    
    <BrowserRouter>
      <header class="header">
        <Link to="/">
          <Button variant="outlined">Home</Button>
        </Link>
        <Link to="/Search">
          <Button variant="outlined">도서검색</Button>
        </Link>
        <Link to="/Use">
          <Button variant="outlined">도서관이용</Button>
        </Link>
        <Link to='/Propose'>
          <Button variant="outlined">도서추천</Button>
        </Link>
        <Link to='/Notice'>
          <Button variant="outlined">공지사항</Button>
        </Link>
      </header>
      <hr/>
      <main>
        <Switch>
        <Route exact path="/" component={Home}/>
        <Route path="/Search" component={Search}/>
        <Route path="/Use" component={Use}/>
        <Route path="/Propose" component={Propose}/>
        <Route path="/Notice" component={Notice}/>
        </Switch>
      </main>
    </BrowserRouter>

    
    
  );
}

export default HansungLibrary;
