import React from 'react'
import { Dropdown } from 'semantic-ui-react'


class CategoryMenu extends React.Component{
    constructor(props){
        super(props);
        this.state = {selected: ""};
        this.handleSelection = this.handleSelection.bind(this);
    }

    handleSelection = (e, data) => {
        this.props.onDropdownClick(data);
        this.setState = ({selected: data.text});
    }

    getUniqueCategories = (arr, index) => {

        const unique = arr
             .map(e => e[index])
      
             // store the keys of the unique objects
             .map((e, i, final) => final.indexOf(e) === i && i)
      
             // eliminate the dead keys & store unique objects
            .filter(e => arr[e]).map(e => arr[e]);      
      
         return unique;
    };

    render(props) {
        const { value } = this.state.selected
        let dropdownitems = null;
        if(this.props.auctions.length > 0){
            let uniqueCategories = this.getUniqueCategories(this.props.auctions, "productCategory");
            
            dropdownitems = uniqueCategories.map((category) => (
                {key: category.productId, text: category.productCategory, value: category.productCategory}
           ));
            dropdownitems[dropdownitems.length-1] = {key: "All categories", text: "All categories", value: "All categories"};
        }

        return (
            <div>
                <Dropdown
                    placeholder="Select Category"
                    fluid
                    selection
                    options={dropdownitems}
                    onChange={this.handleSelection}
                    value={value}
                    >
                    
                </Dropdown>
            </div>
            
        );
    
    };
}

    
export {CategoryMenu};
