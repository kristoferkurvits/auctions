import React from "react";

import "./AuctionItem.css";
import { Header, Grid, List } from "semantic-ui-react";
import { host, port } from "../../config";


class AuctionItem extends React.Component{

  state = {
    userName: "",
    bid: "",
    productId: "",
    productName: "",
    response: "",
    invalidInput: ""
  }

  checkForm = () => {
    if(this.state.userName.length > 0 && !this.state.userName.match(/^[0-9!"#¤%&/()=?`;:_*+@£$€{[½^§\]}]+$/) && this.state.bid > 0){
      this.setState({invalidInput: ""});
      return true;
    }
    return false;
  }


  handleSubmit = (event) => {

    this.setState({response: ""});

    const formBody = {
      userName: this.state.userName,
      bid: this.state.bid,
      productId: this.state.productId,
      productName: this.state.productName
    };
    let isFormValid = this.checkForm();

    if (isFormValid){
        fetch(`${host}${port}/users`, {
        method: "POST",
        body: JSON.stringify(formBody),
        headers: {'Content-Type': 'application/json' }
      }).then(response => {
        this.setState({response: response.status})  

      }).catch(error =>{
        this.setState({response: error.status})
      });
    }else{
      let invalidInput = <p style={{"color":"red"}}>Name can only contain alphabetic characters and a hyphen. Bid has to be greater than 0</p>;
      this.setState({invalidInput: invalidInput});
    }

    event.preventDefault();
  }


  handleFormInput = (auction, event) => {

    this.setState({[event.target.name]: event.target.value});
    this.setState({productId: auction.productId, productName: auction.productName});

  }


  render(){

    const { auction, selectedProductId } = this.props;

    let auctionForm = null;

    let response = null;

    switch(this.state.response){
      case 201:
         response = <p style={{"color":"green"}}>Success</p>
         break;
      case 403:
        response = <p style={{"color":"red"}}>The bidding has ended</p>
         break;
      case 500:
        response = <p style={{"color":"red"}}>Something went wrong</p>
         break;
      default:
        response = null;
      
    }

    if (auction.productId === selectedProductId){
      auctionForm = (<form onSubmit={this.handleSubmit}>
        <label>
          Name:
          <input type="text" name="userName" onChange={(event) => this.handleFormInput(auction,event)} value={this.state.name}/>
        </label>
        <label>
          Bid(EUR):
          <input type="number" name="bid" onChange={(event) => this.handleFormInput(auction, event)} value={this.state.bid}/>
        </label>
        <input type="submit" value="Submit" />
        {response}
        {this.state.invalidInput}
      </form>
      );
    }


    var date = new Date(this.props.auction.biddingEndDate);


    return(
      <List.Item className="auctionItem" style={{ padding: 30 }} onClick={() => this.props.selectedAuctionItemHandler(auction.productId)}>
          <Grid>
            <Grid.Column
              width={11}
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "flex-start",
              }}
            >
              <Header as="h3">{auction.productName}</Header>
              <List.Description
               style={{ margin: "20px 0"}}>
                {auction.productDescription}
              </List.Description>
            </Grid.Column>
            <Grid.Column width={5}>
                <h4>Bidding ends at</h4>
                {date.getFullYear() + "-" + (date.getMonth()+1) + "-" +date.getDate() + " " +
                date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()}

                {auctionForm}
                
            </Grid.Column>
          </Grid>
        </List.Item>
      );
    


  }
}

export { AuctionItem };
