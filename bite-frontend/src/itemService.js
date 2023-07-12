import { postData } from './utils';
import bite from './bite';

const basePath = bite.basePath + `items`;

const post = item => postData(basePath, item);

const getItems = () => fetch(basePath);

const deleteItemById = id => fetch(`${basePath}/${id}`, { method: "DELETE" })

export { getItems, post, deleteItemById}