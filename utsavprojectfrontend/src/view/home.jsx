import React from "react";
import { Dropdown,Table } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css'
import route from '../routes/route.js';

class Home extends React.Component{
    constructor(props){
        super(props);
        this.state={productSearch:"",productSearchResponse:[],productSearchResultBySource:[],source:[],productResultForSelectedSource:[]};
    }
    productSearch(){
        var promise = new Promise((resolve,reject) => {
            route.Search.productSearch(this.state.productSearch).end((error,response) =>{
                if(error){
                    reject(error);
                }
                else{
                    resolve(response);
                }
            })
        }).
        then((response) =>{
            console.log(response);
            this.setState({productSearchResponse:response.body});
            let tempProductSearchResult=response.body;
            let tempProductSearchResultBySource=[];
            this.state.source=[];
            for(let i=0;i<tempProductSearchResult.length;i++){
                let index = tempProductSearchResult[i].source;
                
                if(tempProductSearchResultBySource[index]==null){
                    tempProductSearchResultBySource[index] = [];
                    this.state.source.push({
                        "key":index,
                        "value":index,
                        "text":index
                    });
                }
                tempProductSearchResultBySource[index].push(tempProductSearchResult[i]);
            }
            this.setState({productSearchResultBySource:tempProductSearchResultBySource});
        })
        .catch((error) =>{
            console.log(error);
        })
    }

    render(){
        return(
            <div>
                <input style={{width:'75%'}} title="Search" id="productSearch" onChange={(e) =>{this.setState({productSearch:e.target.value})}} placeholder="search..."></input>
                <button onClick={this.productSearch.bind(this)}>Search</button>

                <Dropdown placeholder='Source' onChange={(e,data) =>{
                    for(let i=0;i<data.value.length;i++){
                        this.state.productResultForSelectedSource.push(this.state.productSearchResultBySource[data.value[i]])
                    }
                    this.setState({productResultForSelectedSource:this.state.productResultForSelectedSource});
                }} fluid search multiple selection options={this.state.source} />
                <Table>
                    <Table.Header>
                        <Table.HeaderCell></Table.HeaderCell>
                    </Table.Header>
                </Table>
                
            </div>
        );
    }
}
export default Home;