update shipment set price_tag =
(SELECT (CASE WHEN shipment.total_cost / shipment.volume < price.regular_price THEN 'PROMO' ELSE 'REGULAR' END)
from price where shipment.material_id = price.material_id and shipment.chain_name = price.chain_name)