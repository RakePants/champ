import axiosInstance from ".";

async function updateTicketStatus(status, id) {
    const response = await axiosInstance.put(`/tickets/${status}?id=${id}`)
    .catch(err => {
        console.log(err);
    });

    return response.data;
}

export async function accept(id, contractor_id, completion_target_date) {
    const response = await axiosInstance.put(`/tickets/accept`,{contractor_id, completion_target_date}, { params:{id} })
    .catch(err => {
        console.log(err);
    });

    return response.data;
}

export async function decline(id) {
    return updateTicketStatus('decline', id);
}

export async function complete(id, completion_image) {
    const response = await axiosInstance.put(`/tickets/complete`,completion_image, { params:{id} })
    .catch(err => {
        console.log(err);
    });

    return response.data;
}
