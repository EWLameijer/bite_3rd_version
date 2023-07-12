import { useEffect, useState } from 'react';
import { deleteItemById, getItems } from './itemService';
import AddItem from './AddItem';
import { formatAsPrice, toInternalCentPrices } from './utils';
import { Link } from 'react-router-dom'

const Manage = () => {
    const [items, setItems] = useState([]);
    const [versionGuid, updateVersionGuid] = useState(0)

    const reloadItems = () => updateVersionGuid(versionGuid + 1)

    const deleteItem = id => deleteItemById(id).then(reloadItems);

    useEffect(() => {
        getItems().then(response => response.json())
            .then(actualData => setItems(toInternalCentPrices(actualData)))
            .catch(err => console.log(`An error has occurred: ${err.message}.`))
    }, [versionGuid]);

    return <>
        <p>Manage!</p>
        <Link to="/">Order now...</Link>
        <ol>
            {items.map(item => <li key={item.id}>{item.name} {formatAsPrice(item.price)}<button onClick={() => deleteItem(item.id)}>Delete</button></li>)}
        </ol>
        <AddItem reloadItems={reloadItems} />
    </>
    // note: <AddItem {...{reloadItems}} /> also works, but may be too unconventional for readability, {reloadItems} itself does NOT work
}

export default Manage;
