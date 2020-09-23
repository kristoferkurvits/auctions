import React from "react";
import { getAuctions } from "./api"
import { Container, Header } from "semantic-ui-react";
import { AuctionList } from "./components/AuctionList"
import { CategoryMenu } from "./components/CategoryMenu"
class App extends React.Component {

  constructor(props) {
    super(props);

    this.handleDropdownClick = this.handleDropdownClick.bind(this);

    this.state = {
      auctions: [],
      apiError: "",
      selectedAuctions: [],
      selectedProductId: null
    };
  };

  async componentDidMount(){
    try {
      const response = await getAuctions();
      this.setState({auctions: response});
    } catch (error) {
      this.setState({ apiError: "Could not find any auctions" });
    }
  }


  auctionsExists(auctions){
    return auctions.length > 0;
  }

  async handleDropdownClick(data){

    let auctions = [...this.state.auctions];

    if (data.value !== "All categories"){
      auctions = auctions.filter((elem) => {
        return elem.productCategory === data.value;
      });
      this.setState({selectedAuctions: auctions});
      
    } else{
        try {
          const response = await getAuctions();
          this.setState({auctions: response, selectedAuctions: []});
        } catch (error) {
          this.setState({ apiError: "Could not find any auctions" });
        }
    }

  }

  selectedAuctionItemHandler = (productId) =>{
    this.setState({selectedProductId: productId});
  }




  render() {
    const { auctions, apiError, selectedAuctions, selectedProductId } = this.state;
    const renderAuctions = () => {

      if(this.auctionsExists(auctions)){
        return <React.Fragment>
          <CategoryMenu auctions={auctions} onDropdownClick={this.handleDropdownClick}/>
          {this.state.selectedAuctions.length
           ?
           <AuctionList auctions={selectedAuctions} 
           selectedAuctionItemHandler={this.selectedAuctionItemHandler} 
           selectedProductId={selectedProductId} />
            :
            <AuctionList auctions={auctions}
             selectedAuctionItemHandler={this.selectedAuctionItemHandler}
              selectedProductId={selectedProductId}/>}
          
        </React.Fragment>
        
      };

    };
    return (
      <Container>
        <Header as="h2" style={{ textAlign: "center", margin: 20 }}>
          Auctions
        </Header>

        {renderAuctions()}
        
        {apiError && <p>Could not fetch any articles. Please try again.</p>}
        
      </Container>
    );
  }
}

export default App;