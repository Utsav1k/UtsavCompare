import React from 'react';
import Home from './view/home.jsx';

class App extends React.Component{

    constructor(props){
      super(props);
    }
    
    render(){
      return(
        <div>
          <Home />
          
        </div>
      )
    }
}
export default App;