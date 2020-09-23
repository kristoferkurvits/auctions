import React from "react";
import { List } from "semantic-ui-react";
import { AuctionItem } from "./AuctionItem/AuctionItem";


const AuctionList = (props) => {

  return (
    <List divided style={{ maxWidth: 900, margin: "0 auto" }}>
      {props.auctions.map((auction) => (
        <AuctionItem auction={auction} selectedAuctionItemHandler={props.selectedAuctionItemHandler} key={auction.productId} selectedProductId={props.selectedProductId}/>
      ))}
    </List>
  );
};

export {AuctionList};