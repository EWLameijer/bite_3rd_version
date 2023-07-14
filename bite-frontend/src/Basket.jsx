import { formatAsPrice } from './utils';
import PropTypes from 'prop-types';

const Basket = ({ items, basket }) => {
    const orders = items.filter(item => basket[item.id]).map(item => ({ name: item.name, count: basket[item.id], linePrice: basket[item.id] * item.price }))
    const orderLines = orders.map(order => `${order.count} ${order.name} for ${formatAsPrice(order.linePrice)}`);
    const totalPrice = orders.reduce((runningTotal, currentItem) => runningTotal + currentItem.linePrice, 0);

    return <>
        Your basket{Object.keys(basket).length === 0 ? " is empty" :
            (<>
                :
                <ul>
                    {orderLines.map(orderLine => <li key={orderLine}>{orderLine}</li>)}
                </ul>
                <p>TOTAL: {formatAsPrice(totalPrice)}</p>
            </>
            )
        }
    </>
}

Basket.propTypes = {
    items: PropTypes.array.isRequired,
    basket: PropTypes.object.isRequired
}

export default Basket;