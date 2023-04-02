//router
import {Button} from '@mui/material';
import './App.css';
//라우팅할 페이지 불러오기
import {Link, BrowserRouter, Switch, Route} from 'react-router-dom';
import Home from './pages/Home.js';
import Videos from './pages/Videos.js';
import Books from './pages/Books.js';
//import Notice from './pages/Notice.js';
import BookLent from './pages/BookLent.js';
import VideoLent from './pages/VideoLent';
import Members from './pages/Members';

function HansungLibrary() {
  return (
    
    <BrowserRouter>
      <header class="header">
        <Link to="/">
          <Button variant="outlined">Home</Button>
        </Link>
        <Link to="/Videos">
          <Button variant="outlined">영상관리</Button>
        </Link>
        <Link to="/BookLent">
          <Button variant="outlined">도서대출</Button>
        </Link>
        <Link to='/Books'>
          <Button variant="outlined">도서관리</Button>
        </Link>
        <Link to='/VideoLent'>
          <Button variant="outlined">영상대출</Button>
        </Link>
        <Link to='/Members'>
          <Button variant="outlined">회원관리</Button>
        </Link>
      </header>
      <hr/>
      <main>
        <Switch>
        <Route exact path="/" component={Home}/>
        <Route path="/Videos" component={Videos}/>
        <Route path="/BookLent" component={BookLent}/>
        <Route path="/Books" component={Books}/>
        <Route path="/VideoLent" component={VideoLent}/>
        <Route path="/Members" component={Members}/>
        </Switch>
      </main>
    </BrowserRouter>

    
    
  );
}

export default HansungLibrary;
