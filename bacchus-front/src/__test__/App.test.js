import { shallow, mount } from "enzyme";
import React, { Component } from 'react';
import App from "../App";
import { setupTest } from "../setupTest";
import { CategoryMenu } from "../components/CategoryMenu";
import renderer from 'react-test-renderer';
import { Form } from 'semantic-ui-react';



describe("App component and its children render", () => {
    it("renders App component without crashing", () => {
        shallow(<App />);
    
    });

    it("renders App correctly", () => {
        const tree = renderer
        .create(<App />)
        .toJSON();

        expect(tree).toMatchSnapshot();
      });
      
    it("renders entire application", () => {
        mount(<App />);
    });
})




describe("Dropdown menu", () => {
    const auction = {
        "productId": "036d9cc3-b0a3-442c-96ac-4291b3ac2894",
        "productName": "MSI GeForce GTX 1080",
        "productDescription": "Average MSI GeForce GTX 1080",
        "productCategory": "Computer hardware",
        "biddingEndDate": "2020-09-21T11:00:07Z"
    };
    it("Data appears in dropdown from props", () => {
        const wrapper = mount(<CategoryMenu auctions={auction} />);
        const value = wrapper.find(CategoryMenu).props().auctions["productName"];

        expect(value).toBe("MSI GeForce GTX 1080");


    });

    it("Dropdown handler works", () => {
        const value = "value";
        const mockMyEventHandler = jest.fn();
        const wrapper = mount(<CategoryMenu auctions={auction} onChange={mockMyEventHandler}/>);
        wrapper.prop("onChange")(value);

        expect(mockMyEventHandler).toHaveBeenCalledWith(value);
        
    });

    

});



